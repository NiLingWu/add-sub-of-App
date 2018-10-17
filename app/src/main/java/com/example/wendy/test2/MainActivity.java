package com.example.wendy.test2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BeginFragment.CallBackValue{

    private TextView txt_home;
    private TextView txt_set;
    private TextView txt_his;//为“我的”组件
    private FrameLayout ly_content;

    private MyFragment fg2;
    private InformationFragment fg3;
    private BeginFragment fg1;
    private FragmentManager fManager;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fManager = getFragmentManager();
        bindViews();
        txt_home.performClick();////模拟一次点击，既进去后选择第一项

    }

    void bindViews()
    {
        txt_home=(TextView)findViewById(R.id.home);
        txt_set = (TextView)findViewById(R.id.setting);
        txt_his = (TextView)findViewById(R.id.history);

        txt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                setSelected();
                txt_home.setSelected(true);
                if(fg1 == null){
                    fg1 = new BeginFragment();//第一个
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                fTransaction.commit();
            }
        });

        txt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                setSelected();
                txt_set.setSelected(true);
                if(fg2 == null){
                    fg2 = new MyFragment();//第二个
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                fTransaction.commit();
            }
        });

        txt_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                setSelected();
                txt_his.setSelected(true);
                if(fg3 == null){
                    fg3 = new InformationFragment();//第三个
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                fTransaction.commit();
            }
        });


    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_home.setSelected(false);
        txt_set.setSelected(false);
        txt_his.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }

    //传递数据到ChallengeActivity
    @Override
    public void SendMessageValue(String str) {
        // TODO Auto-generated method stub
        //将str切成三个数据
        String a,b,c;
        String[] str2 = str.split(" 题");
        String[] str3 = str2[1].split("的");
        String[] str4 = str3[1].split("计算");
        a = str2[0];
        b = str3[0];
        c = str4[0];

        //传递数据
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putInt("num",Integer.parseInt(a));
        bundle.putString("wei",b);
        bundle.putString("style",c);
       intent.putExtras(bundle);
        intent.setClass(this,ChallengeActivity.class);
        startActivity(intent);

    }

}
