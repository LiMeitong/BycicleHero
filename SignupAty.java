package com.course.byciclehero;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SignupAty extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_aty);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button signUpButton = (Button) findViewById(R.id.signupAtyBtn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupAty.this,ShowPage.class);
                startActivity(intent);
                finish();
            }
        });
        TextView loginText = (TextView) findViewById(R.id.logintextBtn);
        loginText.setText("  << Log in");
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(SignupAty.this,MainActivity.class);
                        startActivity(intent);
                        finish();
            }
        });


    }

}
