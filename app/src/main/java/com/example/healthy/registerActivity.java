package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthy.service.UserService;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button buttonRegister=(Button)findViewById(R.id.button);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register(){
        EditText editName=(EditText)findViewById(R.id.editTextTextPersonName);
        EditText editPassword=(EditText)findViewById(R.id.editTextTextPersonName2);
        EditText editPassword2=(EditText)findViewById(R.id.editTextTextPersonName7);
        String name=editName.getText().toString();
        if(name.length()<1){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = editPassword.getText().toString();
        String pwd2 = editPassword2.getText().toString();
        if (pwd.length() < 1){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pwd.equals(pwd2)){
            Toast.makeText(this,"确认密码与原密码不一致",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Validator.isUserName(name)){
            Toast.makeText(this,"用户名必须以字母开头，长度在4-15之间，允许字母数字下划线",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Validator.isPassword(pwd)){
            Toast.makeText(this,"密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在6-16之间",Toast.LENGTH_LONG).show();
            return;
        }
        String username=editName.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        Log.i("TAG",username+"_"+password);
        UserService uService=new UserService(registerActivity.this);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        uService.register(user);
        Toast.makeText(registerActivity.this,"注册成功",Toast.LENGTH_LONG).show();
        finish();
    }
}