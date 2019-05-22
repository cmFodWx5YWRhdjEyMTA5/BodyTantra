package com.getubusiness.bodytantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.getubusiness.R;
import com.getubusiness.bodytantra.Booking;

public class CustomServiceAdapter extends ArrayAdapter {
    Context context;
    String[] array;
    public CustomServiceAdapter(Context context, int resource,String[] array) {
        super(context, resource,array);
        this.context=context;
        this.array=array;
    }


    @Override
    public View getView(int position,   View convertView,   ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.services_list, parent, false);
       TextView tx=convertView.findViewById(R.id.services_list_txt);
       Button btn=convertView.findViewById(R.id.services_list_btn);
       tx.setText(array[position]);
        return  convertView;
        //return super.getView(position, convertView, parent);
    }
}
