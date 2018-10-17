package com.example.wendy.test2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wendy on 2018/4/22.
 */

public class BeginFragment extends Fragment {
    private TextView content;
    private Button btn;
    CallBackValue call;
    public BeginFragment()
    {

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try{
            call=(CallBackValue)getActivity();
        }
        catch (ClassCastException e)
        {
            throw  new ClassCastException(activity.toString()+"must implement CallBackValue");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.begin_content,container,false);
        content = (TextView) view.findViewById(R.id.challengeTip);
        btn = (Button)view.findViewById(R.id.btn_begin);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call.SendMessageValue(content.getText().toString());
            }
        });


        return view;
    }

    public interface CallBackValue {
        public void SendMessageValue(String str);
    }
}
