package com.Codepath.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new  View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                return false;
            }

            @Override
            public void onClick(View v) {
                Log.i(TAG ,"on click login button");
                String username= etUsername.getText().toString();
                String password= etPassword.getText().toString();
                loginUser(username,password);
            }
    private void loginUser(String username , String password){
                Log.i(TAG,"attempting to login user"+username);
                ParseUser.logInBackground(username,password,new LogInCallback()){
                    @Override
        }           public void done(ParseUser user , ParseException e){
                    if (e != null){
                        Log.e(TAG,"issue with login " e);
                        return;
                    }
                    private void goMainActivity{
                        Intent i= new Intent(this,MainActivity.class);
                        startActivity(i);
                        finish()
            }
        }
    }
        });
    }

    {


    }
}
