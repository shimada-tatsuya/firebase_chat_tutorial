package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //views
    Button mRegisterBtn,mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //端末の"LocalData3"という名前のSharedPreferences型インスタンスを取得。MODE_PRIVATE：他のアプリからアクセスできない
        SharedPreferences prefForOutput =getSharedPreferences("LocalUserId",MODE_PRIVATE);
        //第一引数：取得したいvalueを格納しているkey名、第二引数：もしそのキーの値が存在しないときの初期値とする値
        String str = prefForOutput.getString("userId", "");
        Log.d("prefTest","prefTest(before):"+str);

        //キーであるuserIdに値がない場合（初回登録がされていない場合）
        if(str.equals("")){
            SharedPreferences prefForInput = getSharedPreferences("LocalUserId",MODE_PRIVATE);
            SharedPreferences.Editor e = prefForInput.edit();
            e.putString("userId", "初回登録成功");
            e.commit();
            Log.d("prefTest","prefTest(after):"+prefForOutput.getString("userId", ""));
        }


        //init views
        mRegisterBtn = findViewById(R.id.register_btn);
        mLoginBtn=findViewById(R.id.login_btn);

        //handle register button click
        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //start RegisterActivity
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));

            }
        });
        //handle login button click
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start LoginActivity
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

            }
        });

    }
}