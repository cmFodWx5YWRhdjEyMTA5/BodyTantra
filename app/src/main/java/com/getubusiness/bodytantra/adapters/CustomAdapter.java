package com.getubusiness.bodytantra.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getubusiness.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList fruitImages;
   // String[] fruitNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList fruitImages) {
        this.context = applicationContext;
        this.fruitImages = fruitImages;
      //  this.fruitNames = fruitNames;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return fruitImages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.layout_flipper, null);
       // TextView fruitName = (TextView) view.findViewById(R.id.fruitName);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.imageview_flipper);
       // fruitName.setText(fruitNames[position]);
        fruitImage.setImageResource((int)fruitImages.get(position));
        return view;
    }
}