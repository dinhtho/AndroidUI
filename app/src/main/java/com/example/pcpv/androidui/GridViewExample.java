package com.example.pcpv.androidui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_example);
        GridView gridView = findViewById(R.id.gridview);
        final List<String> stringList =new ArrayList<>();

        for(int i=0;i<10;i++){
            stringList.add("Icon "+i);
        }
        GridViewAdapter gridViewAdapter =new GridViewAdapter(this,stringList);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(GridViewExample.this,stringList.get(i),Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });



    }
}

class GridViewAdapter extends BaseAdapter {
    private List<String> stringList;
    private Context context;

    public GridViewAdapter(Context context, List<String> stringList) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.tv);
            textView.setText(stringList.get(i));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.image);
            imageView.setImageResource(R.mipmap.ic_launcher);


        } else {
            gridView = (View) convertView;
        }

        return gridView;

    }
}
