package com.example.florian.exam_240517;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by florian on 24/05/2017.
 */

public class CustomAdapter extends ArrayAdapter<Object_Classes.Contacts> {

    private ArrayList<Object_Classes.Contacts> Item_list;

    @Override
    public View getView(int position, View ConvertView, ViewGroup parent){
        View v = ConvertView;
        ViewHolder holder ;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_element, parent, false);
            holder = new ViewHolder() ;
            holder.id = (TextView)v.findViewById(R.id.List_Id);
            holder.nom = (TextView)v.findViewById(R.id.List_Name);
            holder.adresse = (TextView)v.findViewById(R.id.List_Adress);
            holder.mail = (TextView)v.findViewById(R.id.List_Mail);
            holder.genre = (TextView)v.findViewById(R.id.List_Gender);
            v.setTag(holder) ;
        }else{
            holder = (ViewHolder)v.getTag() ;
        }
        final Object_Classes.Contacts randomguy = Item_list.get(position);
        if(randomguy != null){
            holder.id.setText(randomguy.getId());
            holder.nom.setText(randomguy.getCName());
            holder.adresse.setText(randomguy.getAdress());
            holder.mail.setText(randomguy.getMail());
            holder.genre.setText(randomguy.getGender());
        }
        return v;

    }

    public CustomAdapter(Context context, ArrayList<Object_Classes.Contacts> derp) {
        super(context, R.layout.list_element, derp);
        this.Item_list = derp;
    }

    static class ViewHolder{
        TextView id;
        TextView nom;
        TextView adresse;
        TextView mail;
        TextView genre;

    }

    public int getCount(){
        return Item_list.size();
    }


}
