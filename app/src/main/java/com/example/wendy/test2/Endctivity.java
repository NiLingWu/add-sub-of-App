package com.example.wendy.test2;

import android.content.Intent;
import android.sax.TextElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Endctivity extends AppCompatActivity {

    private TextView tvtime;
    private TextView tvdui;
    private TextView tvcuo;
    private TextView tvwan;
    private TextView tvback;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        final Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        String time=bundle.getString("time");
        int dui=bundle.getInt("dui");
        int all=bundle.getInt("total");
        String wan=bundle.getString("wanzheng");
        ArrayList<HashMap<String, Object>> list =((ArrayList<HashMap<String, Object>>)bundle.getSerializable("arrayList"));

        tvtime=(TextView)findViewById(R.id.tv_time) ;
        tvdui= (TextView)findViewById(R.id.tv_right);
        tvcuo=(TextView)findViewById(R.id.tv_wrong);
        tvwan=(TextView)findViewById(R.id.tv_wan);
        tvtime.setText(time);
        tvdui.setText(String.valueOf(dui));
        tvcuo.setText(String.valueOf(all-dui));
        tvwan.setText(wan);

        tvback=(TextView)findViewById(R.id.tv_back);

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Endctivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        listview=(ListView)findViewById(R.id.showlist);
        SimpleAdapter simpleAdapter=new SimpleAdapter(Endctivity.this,list,R.layout.listview_item,new String[]{"shizi","zhi"},new int[]{R.id.shizi,R.id.daan});
        listview.setAdapter(simpleAdapter);

    }
}
