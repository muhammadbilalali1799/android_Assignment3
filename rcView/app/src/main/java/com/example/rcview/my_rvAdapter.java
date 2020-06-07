package com.example.rcview;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class my_rvAdapter extends RecyclerView.Adapter<my_rvAdapter.ViewHolder> {
    Context context;
    private ArrayList<String> Title;
    private ArrayList<String> Label;
    private ArrayList<String> Info;
    private String basePath="https://raw.githubusercontent.com/revolunet/PythonBooks/master/";
    private ArrayList<String> imageName;

    public my_rvAdapter( ArrayList<ArrayList<String>> data)
    {
        this.Title=data.get(0);
        this.Label=data.get(1);
        this.Info=data.get(2);
        this.imageName=data.get(3);//imageName
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout , parent,false);
        return new ViewHolder(view);

    }
    public void onBindViewHolder(@NonNull ViewHolder holder,int position)
    {
        holder.tv_bookTitle.setText(Title.get(position));
        holder.tv_bookLevel.setText(Label.get(position));
        holder.tv_bookInfo.setText(Info.get(position));
        //picasso image uploading
        Picasso.get().load(basePath+imageName.get(position)).into(holder.img);
    }

    public int getItemCount()
    {
        return Title.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_bookTitle;
        public TextView tv_bookLevel;
        public TextView tv_bookInfo;
        public ImageView img;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_bookTitle=itemView.findViewById(R.id.bookTitle);
            tv_bookLevel=itemView.findViewById(R.id.bookLevel);
            tv_bookInfo=itemView.findViewById(R.id.bookInfo);
            img=itemView.findViewById(R.id.img);
            //_img=itemView.findViewById(R.id.img);
        }
    }
}
