package com.example.mayada.recyclerviewexample.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mayada.recyclerviewexample.R;

/**
 * Created by Mayada on 7/25/2018.
 */


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    String [] returnedPosters=null;
    String [] returnedNames = null;

    public DataAdapter(Context context,String[] posters, String[]names) {
        this.context = context;
        this.returnedNames=names;
        this.returnedPosters=posters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(returnedNames[position]);

        // show Image using picasso , not easy with OkHTTP request image url , we can use Glide instead
      //  PicassoTrustAll.getInstance(context).load(pojoArrayList.get(position).getAndroid_image_url()).placeholder(R.drawable.memo).resize(120, 60).into(holder.img);
        Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+returnedPosters[position]).placeholder(R.drawable.memo)
              .into(holder.img);

    }

    @Override
    public int getItemCount() {
        if(returnedNames!=null) {
            return returnedNames.length;
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.myText);
            img = (ImageView)itemView.findViewById(R.id.myImg);
        }
    }
}
