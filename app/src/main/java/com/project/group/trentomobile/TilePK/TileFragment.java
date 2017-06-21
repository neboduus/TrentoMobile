package com.project.group.trentomobile.TilePK;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.group.trentomobile.Classi.Evento;
import com.project.group.trentomobile.Classi.Fermata;
import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.R;
import com.project.group.trentomobile.Repository.TileMemoryRep;
import com.project.group.trentomobile.TailActivity;
import com.project.group.trentomobile.Util.InternalStorage;
import com.project.group.trentomobile.Util.ScaricaImmagine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by postal on 25/04/17.
 * Defined Fragment extension for Tile which is a content element of the Pages
 */

public class TileFragment extends Fragment {

    TextView titolo;
    TextView corpo;
    ImageView immagine;
    TextView piedi;
    Button opt;

    private static final String TILE_KEY = "tile_key";

    //it contains a Tile private element which contains data for populating children view elements
    private Tile data;

    public static TileFragment newInstance(Tile t) {
        TileFragment fragment = new TileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TILE_KEY, t);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    //the fragment creates its view hierarchy
    // inflated views become part of the view hierarchy of its containing activity
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //inflate a new view hierarchy from the specified xml resource
        final View r =  inflater.inflate(R.layout.tile_fragment, container, false);

        //get the construction arguments
        data = (Tile) getArguments().getSerializable(TILE_KEY);

        //recover the others xml resources which are tchildrens of R.layout.tile_fragment
        titolo = (TextView) r.findViewById(R.id.titolo);
        corpo = (TextView) r.findViewById(R.id.corpo);
        piedi = (TextView) r.findViewById(R.id.piedi);
        immagine = (ImageView) r.findViewById(R.id.immagine);
        opt = (Button) r.findViewById(R.id.opt);

        //register a context menu to be shown for the given view
        registerForContextMenu(opt);


        titolo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               // Log.d("daiii----------------", String.valueOf(event.getAction()));
                Intent myIntent = new Intent(getActivity(), TailActivity.class);
                //the name of optional parameters must include a package prefix (Ex. 'com.project.group.trentomobile.TilePK.TileData')
                myIntent.putExtra("data", data); //Optional parameters
                getActivity().startActivity(myIntent);
                return false;
            }
        });

 /*       opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titolo.setText("lol");
            }
        });
        */


        //setting the footer with respect to the kind of the data contained in the tile
        String sPiedi ="";
        if(data instanceof Notizia){
            Notizia n = (Notizia) data;
            DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
            sPiedi += "" + n.getAutore().getNome() + " - " + formatter.format(n.getData().getTimeInMillis());
        }else
        if(data instanceof Luogo){
            Luogo l = (Luogo) data;
            sPiedi += l.getIndirizzo().getVia();
        }else
        if(data instanceof Evento){
            Evento e = (Evento) data;
            DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd hh:mm");
            sPiedi += e.getIndirizzo().getVia() +" - "+formatter.format(e.getData().getTimeInMillis());
        }else
        if(data instanceof Fermata){
            //SETTA PIEDI

        }

        titolo.setText(data.getTitolo());
        corpo.setText(data.getDescrizione());
        piedi.setText(sPiedi);
        String nomeImg = "tileid"+data.getId();
        if(data instanceof Fermata)
                nomeImg ="bus3";
        new ScaricaImmagine((ImageView) immagine).execute(data.getPatterImmagine(), nomeImg);
        return r;
    }

    //creating the menu buttons for the tile
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //should check differentiation with Fermata Tile
        menu.add(data.getId(),1,0,"Condividi");
        menu.add(data.getId(),2,0,"Non mi interessa");
        menu.add(data.getId(),3,0,"Aggiungi ai preferiti");
        menu.add(data.getId(),4,0,"Invia feed-back");
        /*
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_tile, menu);
        */
    }

    //handling the tiles menu item selection
    //called whenever an item in a context menu is selected
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int idd = item.getGroupId();
        Preferenze myPreference = null;

        //now we are modifying the preferences with respect on the item menu selected in the tile

        switch (item.getItemId()) {
            case 1: //CONDIVIDI

                Tile t = TileMemoryRep.getInstance().getTileById(idd);

                Log.d("iddddd", String.valueOf(idd));

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, t.getTitolo()+"---"+t.getDescrizione()+"---"+t.getPatterImmagine());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            return true;

            case 2: //NON MI INTERESSA

                try {
                    myPreference = (Preferenze) InternalStorage.readObject(getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Tile d = TileMemoryRep.getInstance().getTileById(idd);

                Log.d("ttitolooooo->", d.getTitolo());

                    if(d instanceof Notizia){
                        Genere_Notizia gn = ((Notizia) d).getGenere();
                        myPreference.getPref_Notizie().put(gn.getTipo(),0);
                    }else
                    if(d instanceof Luogo){
                        Genere_Luogo gn = ((Luogo) d).getGenere();
                        myPreference.getPref_Luoghi().put(gn.getTipo(),0);
                    }else
                    if(d instanceof Evento){
                        Genere_Evento gn = ((Evento) d).getGenere();
                        myPreference.getPref_Eventi().put(gn.getTipo(),0);
                    }if(d instanceof Fermata){
                        myPreference.setPref_Trasporti_False();
                    }else{
                        Log.d("ERRORACCIO","ritorna la superclasse");
                    }

                try {
                    InternalStorage.writeObject(getActivity(),myPreference);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                Log.d("tagid->", String.valueOf(data.getId())+ "    "+data.getTitolo()+ "     "+ idd);

                FragmentTransaction ft = this.getFragmentManager().beginTransaction();

                ft.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);

                ft.hide(getFragmentManager().findFragmentByTag(String.valueOf(idd)))
                        .addToBackStack(null)
                        .commit();


                return true;

            case 3 : //AGGIUNGI A PREFERITI

                myPreference = null;
                try {
                    myPreference = (Preferenze) InternalStorage.readObject(getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }



                myPreference.addPreferiti(idd);

                for(int i:myPreference.getIdsPreferiti()){
                    Log.d("pref", String.valueOf(i));
                }



                try {
                    InternalStorage.writeObject(getActivity(),myPreference);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            return true;

            case 4: //FEEDBACK

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sptanco@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feed-back for tile with id:"+ idd);
                i.putExtra(Intent.EXTRA_TEXT   , "Good morning, this is my feed-back about the tile whith id:" + idd +".\n");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            return true;

            default:
                return super.onContextItemSelected(item);
        }







    }




    @Override
    public void onResume() {
        super.onResume();
    }
}
