package com.getubusiness.bodytantra;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.getubusiness.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>  {
  // private ArrayList al=new ArrayList();
   private List images=new ArrayList();
   private Context context;

    public CustomRecyclerAdapter(Context context, ArrayList images) {
     //   this.al = al;
        this.images = images;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recycler,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerAdapter.ViewHolder viewHolder, int i) {
//viewHolder.itemView.setTag(al.get(i));
viewHolder.itemView.setTag(images.get(i));

//ViewHolder.setText(pu.getPersonName());
        //viewHolder.pJobProfile.setText(pu.getJobProfile());
        viewHolder.imageView.setImageResource((Integer) images.get(i));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

           // pName = (TextView) itemView.findViewById(R.id.textview_recycler);
            imageView = (ImageView) itemView.findViewById(R.id.imageview_recycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  //  ArrayList images = (ArrayList) view.getTag();

                  //  Toast.makeText(view.getContext(), images.size(),Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
