package com.example.rcview;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class my_rvAdapter extends RecyclerView.Adapter<my_rvAdapter.ViewHolder> {
    Context context;

    private ArrayList<String> Title;
    private ArrayList<String> Label;
    private ArrayList<String> Info;
    private ArrayList<String> urls;
    private String basePath="https://raw.githubusercontent.com/revolunet/PythonBooks/master/";
    private ArrayList<String> imageName;

    public my_rvAdapter(Context context, ArrayList<ArrayList<String>> data)
    {
        this.context=context;
        this.Title=data.get(0);
        this.Label=data.get(1);
        this.Info=data.get(2);
        this.imageName=data.get(3);//imageName
        this.urls=data.get(4);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout , parent,false);
        return new ViewHolder(view);

    }
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.tv_bookTitle.setText(Title.get(position));
        holder.tv_bookLevel.setText(Label.get(position));
        holder.tv_bookInfo.setText(Info.get(position));
        //picasso image uploading
        Picasso.get().load(basePath+imageName.get(position)).into(holder.img);
        String str = urls.get(position).substring(urls.get(position).length() - 4);

        if(str.equals(".zip") || str.equals(".pdf")){
            // set click event on Button and then start downloading file through download manger
            holder.btnD.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String url = urls.get(position);
                    //creating download request

                    DownloadManager downloadManager = (DownloadManager)context.getSystemService(context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(url);
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setTitle("Download");
                    request.setDescription("Downloading Start...");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long ref = downloadManager.enqueue(request);
                }
            });
        }
        else{
            holder.btnD.setText("READ ONLINE");
            //Read on Google Chrome
            holder.btnD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = urls.get(position);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    ContextCompat.startActivity(context, intent, null);
                }
            });
        }

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
        public Button btnD;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_bookTitle=itemView.findViewById(R.id.bookTitle);
            tv_bookLevel=itemView.findViewById(R.id.bookLevel);
            tv_bookInfo=itemView.findViewById(R.id.bookInfo);
            img=itemView.findViewById(R.id.img);
            btnD=itemView.findViewById(R.id.btnDownload);
            //_img=itemView.findViewById(R.id.img);
        }
    }
}
