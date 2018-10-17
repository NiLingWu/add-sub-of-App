package com.example.wendy.test2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wendy on 2018/4/21.
 */

public class MyFragment extends Fragment {

    private SeekBar bar;
    private TextView tipnum;
    private RadioButton style;
    private RadioButton wei;
    private Button reset;
    private Button sure;
    private RadioGroup rStyle;
    private RadioGroup rWei;
    private TextView tv;

    public MyFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fg_content,container,false);

        //seekbar的题目数提示
        bar = (SeekBar)view.findViewById(R.id.bar_num);
        tipnum = (TextView)view.findViewById(R.id.tv_tipnum);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //滚动时
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 10;
                i = i + min;
                tipnum.setText("挑战的题数是：" + i);
            }
            //开始滚动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //停止滚动
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //设置默认值
        style = (RadioButton)view.findViewById(R.id.Rbtn_jia);
        wei = (RadioButton)view.findViewById(R.id.Rbtn_one);
        style.setChecked(true);
        wei.setChecked(true);

        //重置按钮实现
        reset = (Button)view.findViewById(R.id.btn_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                style.setChecked(true);
                wei.setChecked(true);
                bar.setProgress(0);
            }
        });



        //确认按钮保存数据,并传输数据到MyFragment
        sure = (Button)view.findViewById(R.id.btn_sure);
        rStyle = (RadioGroup)view.findViewById(R.id.group_style);//获取RadioGroup组件
        rWei = (RadioGroup)view.findViewById(R.id.group_wei);//获取RadioGroup组件
        tv = (TextView)getActivity().findViewById(R.id.challengeTip);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RadioButton rbtnstyle = (RadioButton)view.findViewById(rStyle.getCheckedRadioButtonId());//获取RadioButton组件
                final RadioButton rbtnwei = (RadioButton)view.findViewById(rWei.getCheckedRadioButtonId());//获取RadioButton组件
                String s = String.valueOf(bar.getProgress()+10);
                String data =  s + " 题" + rbtnwei.getText().toString() + "的" + rbtnstyle.getText().toString() + "计算";
                tv.setText(data);
                Toast.makeText(getActivity(), "设置成功！", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }

    public interface CallBackValue {
        public void SendMessageValue(String str);
    }

}

