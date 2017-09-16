package com.hdmes.crane001;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fusanjie on 2017/9/7 0007.
 */

public class FormActivity extends Activity {
    private TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("监测统计");
        setContentView(R.layout.activity_form);//主窗体
        Intent intent = getIntent();//检索intent赋值给变量
        Bundle bundle = intent.getExtras();//得到intent附带的额外数据
        String str = bundle.getString("username");//返回制定Key值
        tv_username = (TextView) findViewById(R.id.username);
        tv_username.setText(str);

        Button btn2 = (Button) findViewById(R.id.button2);//起重机监测Btn
        btn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(FormActivity.this,CraneActivity.class);
                startActivity(intent1);
            }
        });

    }
}