package com.example.rcview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get recyclerView
        RecyclerView rv =findViewById(R.id.rv);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ArrayList<ArrayList<String>> getTitles=new ArrayList<>();
        getTitles=getJson();

        my_rvAdapter adapter =new my_rvAdapter(this,getTitles);
        rv.setAdapter(adapter);
    }
    public ArrayList<ArrayList<String>> getJson() {
        String json=null;
        try{
            ArrayList<String> title=new ArrayList<>();
            ArrayList<String> level=new ArrayList<>();
            ArrayList<String> info=new ArrayList<>();
            ArrayList<String> imageName=new ArrayList<>();
            ArrayList<String> urls=new ArrayList<>();
            ArrayList<ArrayList<String>> together =new ArrayList<>();

            InputStream is = getAssets().open("data.json");
            int size=is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json=new String(buffer,"UTF-8");
            JSONArray jsonArr=new JSONArray(json);
            for(int i=0;i<jsonArr.length();i++)
            {
                JSONObject jsonobject = jsonArr.getJSONObject(i);
                title.add(jsonobject.getString("title"));
                level.add(jsonobject.getString("level"));
                info.add(jsonobject.getString("info"));
                imageName.add(jsonobject.getString("cover"));
                urls.add(jsonobject.getString("url"));
            }
            together.add(title);
            together.add(level);
            together.add(info);
            together.add(imageName);
            together.add(urls);

            return together;
        }
        catch (Exception EX)
        {
            EX.printStackTrace();
            return null;
        }
    }
}
