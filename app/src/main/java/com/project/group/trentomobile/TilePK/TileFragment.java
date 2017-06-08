package com.project.group.trentomobile.TilePK;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.group.trentomobile.Classi.Evento;
import com.project.group.trentomobile.Classi.Genere_Evento;
import com.project.group.trentomobile.Classi.Genere_Luogo;
import com.project.group.trentomobile.Classi.Genere_Notizia;
import com.project.group.trentomobile.Classi.Luogo;
import com.project.group.trentomobile.Classi.Notizia;
import com.project.group.trentomobile.Classi.Preferenze;
import com.project.group.trentomobile.Classi.Tile;
import com.project.group.trentomobile.R;
import com.project.group.trentomobile.TailActivity;
import com.project.group.trentomobile.Util.InternalStorage;
import com.project.group.trentomobile.Util.ScaricaImmagine;

import java.io.IOException;

/**
 * Created by postal on 25/04/17.
 */

public class TileFragment extends Fragment {



    TextView titolo;
    TextView corpo;
    ImageView immagine;
    TextView piedi;
    Button opt;



    private static final String TILE_KEY = "tile_key";
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View r =  inflater.inflate(R.layout.tile_fragment, container, false);


        data = (Tile) getArguments().getSerializable(
                TILE_KEY);

        titolo = (TextView) r.findViewById(R.id.titolo);
        corpo = (TextView) r.findViewById(R.id.corpo);
        piedi = (TextView) r.findViewById(R.id.piedi);
        immagine = (ImageView) r.findViewById(R.id.immagine);
        opt = (Button) r.findViewById(R.id.opt);

        registerForContextMenu(opt);

        titolo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent myIntent = new Intent(getActivity(), TailActivity.class);
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

        titolo.setText(data.getTitolo());
        corpo.setText(data.getDescrizione());
        piedi.setText(data.getURL());
        new ScaricaImmagine((ImageView) immagine).execute(data.getPatterImmagine());

        return r;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_tile, menu);

    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()) {
            case R.id.mm_NonInteressa:


                Preferenze myPreference = null;
                try {
                    myPreference = (Preferenze) InternalStorage.readObject(getActivity(), "myPreferenze3");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                    if(data instanceof Notizia){

                        Genere_Notizia gn = ((Notizia) data).getGenere();
                        myPreference.getPref_Notizie().remove(gn.getTipo());
                        myPreference.getPref_Notizie().put(gn.getTipo(),Boolean.FALSE);



                    }else
                    if(data instanceof Luogo){

                        Genere_Luogo gn = ((Luogo) data).getGenere();
                        myPreference.getPref_Luoghi().remove(gn.getTipo());
                        myPreference.getPref_Luoghi().put(gn.getTipo(),Boolean.FALSE);


                    }else
                    if(data instanceof Evento){

                        Genere_Evento gn = ((Evento) data).getGenere();
                        myPreference.getPref_Eventi().remove(gn.getTipo());
                        myPreference.getPref_Eventi().put(gn.getTipo(),Boolean.FALSE);

                    }

                try {
                    InternalStorage.writeObject(getActivity(),"myPreferenze3",myPreference);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.mm_feedback :


                FragmentTransaction ft = this.getFragmentManager().beginTransaction();

                ft.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);

                ft.hide(this);

                ft.commit();


                this.getFragmentManager().popBackStack();


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
