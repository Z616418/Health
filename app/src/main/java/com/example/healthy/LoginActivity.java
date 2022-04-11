package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthy.service.UserService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button_register=(Button)findViewById(R.id.button4);
        Button button_login=(Button)findViewById(R.id.button3);
        EditText username=(EditText)findViewById(R.id.editTextTextPersonName4);
        EditText password=(EditText)findViewById(R.id.editTextTextPersonName5);
        button_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                String name=username.getText().toString();
                String pass=password.getText().toString();
                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(name,pass);
                if(flag){
                    Log.i("TAG","登录成功");
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,mainActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","用户名或者密码错误");
                    Toast.makeText(LoginActivity.this,"用户名或者密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
        button_register.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                Intent intent1=new Intent(LoginActivity.this,registerActivity.class);
                startActivity(intent1);
            }
        });
    }
}