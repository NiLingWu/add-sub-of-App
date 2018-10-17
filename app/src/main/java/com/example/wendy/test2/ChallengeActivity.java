package com.example.wendy.test2;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChallengeActivity extends AppCompatActivity {

    private String style, wei;
    private int num;

    private ArrayList<String> shi;
    private ArrayList<Integer> ans;
    private ArrayList<Map<String, Object>> cuoshi;

    private TextView tvshi;
  //  private TextView tvans;
    private TextView tvin;
    private Button btnnext;
    private Button btnclean;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Chronometer chro;
    private TextView tvcount;
    private TextView tvtop;
    private TextView tvback;

    private int mark = 0;
    private int count=0;
    private int weishu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        getValue();
        bingview();

        tvtop.setText(wei+"的"+style+"计算" );
        View.OnClickListener listener = new View.OnClickListener(){
            public  void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btn0:
                        String myStr=tvin.getText().toString();
                        myStr+="0";
                        tvin.setText(myStr);
                        break;
                    case R.id.btn1:
                        String myStr1=tvin.getText().toString();
                        myStr1+="1";
                        tvin.setText(myStr1);
                        break;
                    case R.id.btn2:
                        String myStr2=tvin.getText().toString();
                        myStr2+="2";
                        tvin.setText(myStr2);
                        break;
                    case R.id.btn3:
                        String myStr3=tvin.getText().toString();
                        myStr3+="3";
                        tvin.setText(myStr3);
                        break;
                    case R.id.btn4:
                        String myStr4=tvin.getText().toString();
                        myStr4+="4";
                        tvin.setText(myStr4);
                        break;
                    case R.id.btn5:
                        String myStr5=tvin.getText().toString();
                        myStr5+="5";
                        tvin.setText(myStr5);
                        break;
                    case R.id.btn6:
                        String myStr6=tvin.getText().toString();
                        myStr6+="6";
                        tvin.setText(myStr6);
                        break;
                    case R.id.btn7:
                        String myStr7=tvin.getText().toString();
                        myStr7+="7";
                        tvin.setText(myStr7);
                        break;
                    case R.id.btn8:
                        String myStr8=tvin.getText().toString();
                        myStr8+="8";
                        tvin.setText(myStr8);
                        break;
                    case R.id.btn9:
                        String myStr9=tvin.getText().toString();
                        myStr9+="9";
                        tvin.setText(myStr9);
                        break;
                }

            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);


        shengcheng();

        tvshi.setText(shi.get(0) + "=");
    //    tvans.setText(ans.get(0).toString()+ " " + String.valueOf(mark));
        tvcount.setText("正确个数：" + count + "(" + 0 + "/" + num + ")");
        chro.start();//开始计时


        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChallengeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvin.getText().toString().length()==0)
                {
                    Toast.makeText(ChallengeActivity.this, "请输入答案！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(Integer.parseInt(tvin.getText().toString()) == ans.get(mark))
                    {
                        count++;
                    }
                    else
                    {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("shizi",shi.get(mark)+"="+tvin.getText());
                        map.put("zhi",String.valueOf(ans.get(mark)));
                        cuoshi.add(map);
                    }
                    mark++;
                    tvcount.setText("正确个数：" + count + "(" + mark + "/" + num + ")");

                    if(mark<num)
                    {
                        tvshi.setText(shi.get(mark) + "=");
                     //   tvans.setText(ans.get(mark).toString() + " " + String.valueOf(mark));
                        tvin.setText("");
                    }
                    else
                    {
                        chro.stop();
                        Intent intent = new Intent();
                        Bundle bundle=new Bundle();
                       bundle.putString("time",chro.getText().toString());
                        bundle.putInt("dui",count);
                        bundle.putInt("total",num);
                        String weisure = wei;
                        String stylesure=style;
                        if(weisure.length()==8)
                        {
                            weisure="一位数";
                        }
                        if(stylesure.length()==8)
                        {
                            stylesure="减法";
                        }
                        bundle.putString("wanzheng",weisure+stylesure);
                        bundle.putSerializable("arrayList", cuoshi);
                        intent.putExtras(bundle);
                        intent.setClass(ChallengeActivity.this,Endctivity.class);
                        startActivity(intent);
                    }
                }
            }
        });



        btnclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvin.setText("");
            }
        });
    }

    void sureWei()
    {

        if(wei.length()==3)
        {
            weishu=90;
        }
        else if(wei.length()==8)
        {
            weishu=10;
        }
        else if(wei.length()==6)
            weishu=100;
    }

    void shengcheng()
    {
        int k1,k2;  //k1,k2为两个随机数，产生n个式子
        int ch;       //ch有四种情况
        for(int i=1;i<=num;i++)
        {
            sureWei();
            k1=(int)(Math.random()*weishu);   //随机产生10以内的数
            k2=(int)(Math.random()*weishu);
            if(weishu==90)
            {
                k1=k1+10;
                k2=k2+10;
        }

            if(style.length()==2)
                ch=0;
            else if(style.length()==8)
                ch=1;
            else
                ch=(int)(Math.random()*2);     //随机产生一个运算符号

            switch(ch)
            {
                case 0:
                    shi.add(k1 + "+" + k2);
                    ans.add(k1+k2);
                    break;
                case 1:
                    if(k1>=k2)
                    {
                        shi.add(k1 + "-" + k2);
                        ans.add(k1-k2);
                    }
                    else
                    {
                        shi.add(k2 + "-" + k1);
                        ans.add(k2-k1);
                    }
                    break;
            }
        }

    }

    void bingview()
    {
        shi=new ArrayList<String>();
        ans = new ArrayList<Integer>();
        cuoshi = new ArrayList<Map<String, Object>>();

        tvshi=(TextView)findViewById(R.id.tv_shi);//存放式子
    //    tvans=(TextView)findViewById(R.id.tv_ans);//测试答案和个数
        tvcount=(TextView) findViewById(R.id.tv_count);//记录正确个数
        tvin = (TextView)findViewById(R.id.tv_in2);
        tvtop=(TextView)findViewById(R.id.txt_topbar);
        tvback=(TextView)findViewById(R.id.tv_back);
        chro=(Chronometer)findViewById(R.id.chronometer);

        btnclean = (Button)findViewById(R.id.btn_clean);
        btnnext = (Button)findViewById(R.id.btn_next);
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
    }

    void getValue()
    {
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();

        num=bundle.getInt("num");
        wei=bundle.getString("wei");
        style=bundle.getString("style");
    }
}
