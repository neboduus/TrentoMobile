package com.project.group.trentomobile;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.UpdateRequest;
import com.project.group.trentomobile.Repository.GeneriRepo;
import com.project.group.trentomobile.Util.InternalStorage;

import java.io.IOException;
import java.util.List;

/**
 * A {@link MyPreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: MySettings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">MySettings
 * API Guide</a> for more information on developing a MySettings UI.
 */
public class MyPreferenceActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || TrasportiPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_luoghi);
            setHasOptionsMenu(true);

            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("generi");
            Preferenze myPreference = null;
            try {
                myPreference = (Preferenze) InternalStorage.readObject(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(final Genere_Luogo g : GeneriRepo.getIstance().GeneriLuoghi){

                final SwitchPreference sw = new SwitchPreference(getActivity());
                sw.setTitle(g.getTipo());
                final boolean b = (Boolean)(myPreference.getPref_Luoghi().get(g.getTipo()) > 9);
                sw.setChecked(b);
                sw.setDefaultValue(b);


                Log.d("lollllllll", String.valueOf(b));
                preferenceGroup.addPreference(sw);

                final Preferenze myP = myPreference;

                sw.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                 @Override
                 public boolean onPreferenceChange(final Preference preference, final Object newValue) {

                     UpdateRequest.getInstance().requestUpdate();

                     Boolean newB = (Boolean) newValue;
                     myP.getPref_Luoghi().remove(g.getTipo());
                     myP.getPref_Luoghi().put(g.getTipo(),newB ? 10 : 0);

                     try {
                         InternalStorage.writeObject(getActivity(),myP);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                     sw.setChecked(newB);
                     sw.setDefaultValue(newB);
                 return false;
                 }
                });





            }

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_eventi);
            setHasOptionsMenu(true);

            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("generi");
            Preferenze myPreference = null;
            try {
                myPreference = (Preferenze) InternalStorage.readObject(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(final Genere_Evento g : GeneriRepo.getIstance().GeneriEventi){

                final SwitchPreference sw = new SwitchPreference(getActivity());
                sw.setTitle(g.getTipo());
                final boolean b = (Boolean)(myPreference.getPref_Eventi().get(g.getTipo()) > 9);
                sw.setChecked(b);
                sw.setDefaultValue(b);


                Log.d("lollllllll", String.valueOf(b));
                preferenceGroup.addPreference(sw);

                final Preferenze myP = myPreference;

                sw.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(final Preference preference, final Object newValue) {

                        UpdateRequest.getInstance().requestUpdate();

                        Boolean newB = (Boolean) newValue;
                        myP.getPref_Eventi().remove(g.getTipo());
                        myP.getPref_Eventi().put(g.getTipo(),newB ? 10 : 0);

                        try {
                            InternalStorage.writeObject(getActivity(),myP);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        sw.setChecked(newB);
                        sw.setDefaultValue(newB);
                        return false;
                    }
                });





            }

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notizie);
            setHasOptionsMenu(true);

            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("generi");
            Preferenze myPreference = null;
            try {
                myPreference = (Preferenze) InternalStorage.readObject(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for(final Genere_Notizia g : GeneriRepo.getIstance().GeneriNotizie){

                final SwitchPreference sw = new SwitchPreference(getActivity());
                sw.setTitle(g.getTipo());
                final boolean b = (Boolean)(myPreference.getPref_Notizie().get(g.getTipo()) > 9);
                sw.setChecked(b);
                sw.setDefaultValue(b);


                Log.d("lollllllll", String.valueOf(b));
                preferenceGroup.addPreference(sw);

                final Preferenze myP = myPreference;

                sw.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(final Preference preference, final Object newValue) {

                        UpdateRequest.getInstance().requestUpdate();

                        Boolean newB = (Boolean) newValue;
                        myP.getPref_Notizie().remove(g.getTipo());
                        myP.getPref_Notizie().put(g.getTipo(),newB ? 10 : 0);

                        try {
                            InternalStorage.writeObject(getActivity(),myP);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        sw.setChecked(newB);
                        sw.setDefaultValue(newB);
                        return false;
                    }
                });
            }

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class TrasportiPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notizie);
            setHasOptionsMenu(true);

            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("generi");
            Preferenze myPreference = null;
            try {
                myPreference = (Preferenze) InternalStorage.readObject(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


                final SwitchPreference sw = new SwitchPreference(getActivity());
                sw.setTitle("Fermate");
                final boolean b = (Boolean)(myPreference.getPref_Trasporti() > 9);
                sw.setChecked(b);
                sw.setDefaultValue(b);


                Log.d("lollllllll", String.valueOf(b));
                preferenceGroup.addPreference(sw);

                final Preferenze myP = myPreference;

                sw.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(final Preference preference, final Object newValue) {

                        UpdateRequest.getInstance().requestUpdate();

                        Boolean newB = (Boolean) newValue;
                        if(newB)
                            myP.setPref_Trasporti_Ture();
                        else
                            myP.setPref_Trasporti_False();

                        try {
                            InternalStorage.writeObject(getActivity(),myP);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        sw.setChecked(newB);
                        sw.setDefaultValue(newB);
                        return false;
                    }
                });
            }



        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

}




