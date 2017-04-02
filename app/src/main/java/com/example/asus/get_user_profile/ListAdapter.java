package com.example.asus.get_user_profile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.asus.get_user_profile.R.id.imageView;

/**
 * Created by ASUS on 4/1/2017.
 */

public class ListAdapter extends ArrayAdapter<User> {
    Activity context=null;
    ArrayList<User>myArray=null;
    int layoutId;

    public ListAdapter(Activity context, int layoutId, ArrayList<User> items) {
        super(context, layoutId, items);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_list_item_user, null);
        }
        User u = getItem(position);
        if (u != null) {
            TextView txtName = (TextView) view.findViewById(R.id.textViewName);
            txtName.setText(u.name);
            ImageView imgv = (ImageView)  view.findViewById(imageView);
            // use picasso to load images
           Picasso.with(getContext()).load(u.avatar).into(imgv);

        }
        return view;
    }

}
