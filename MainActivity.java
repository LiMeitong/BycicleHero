package com.course.byciclehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

/**
 * log in activity
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AVOSCloud.initialize(this, "zvqv3u8irntzvpwxu4x43dlsvsxaweomfr6y6vp4hof4bri8", "05npw6yfgep7una9vhhf0ysuqhch0a10dqr5v8f6x95i5w8q");
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            entrance();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText userEditText = (EditText) findViewById(R.id.usereditText);
                EditText userPassword = (EditText) findViewById(R.id.editText);
                String username = userEditText.getText().toString();
                String password = userPassword.getText().toString();

                AVUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(AVUser user, AVException e) {
                        if (user != null) {
                            entrance();
                        } else {
                            Toast.makeText(MainActivity.this,"log in failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        TextView signupText = (TextView) findViewById(R.id.signupBtn);
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SignupAty.class);
                startActivity(intent);
                finish();

            }
        });


    }

    public void entrance(){
        Intent intent = new Intent(MainActivity.this,ShowPage.class);
        startActivity(intent);
        finish();
    }


}
