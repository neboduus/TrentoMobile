package com.project.group.trentomobile.CategoriePK;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.project.group.trentomobile.Classi.Bus;
import com.project.group.trentomobile.Classi.Genere;

import java.util.ArrayList;

/**
 * Created by postal on 20/05/17.
 */

public class CategoriaAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<? extends Genere> generi;

        public CategoriaAdapter(Context c, ArrayList<? extends Genere> generi) {
            mContext = c;
            this.generi = generi;
        }

        public int getCount() {
            return generi.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getView(int position, View convertView, ViewGroup parent) {
            Categoria t = new Categoria(mContext);
            t.setNome(generi.get(position).getTipo());




            t.setImmagine(generi.get(position).getFoto());

            if(generi.get(position) instanceof Bus){
                t.setImmagineColor(((Bus)generi.get(position)).getColor());
            }

            return t;
        }
}

