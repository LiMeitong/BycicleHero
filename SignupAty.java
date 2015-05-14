package com.course.byciclehero;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

/**
 * sign up activity
 */
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


                /**
                 * sign up
                 */
                EditText userEditText = (EditText) findViewById(R.id.usereditText);
                EditText userPassword = (EditText) findViewById(R.id.editText);
                EditText userPasswordRepeat = (EditText) findViewById(R.id.editRepeatText);
                String username = userEditText.getText().toString();
                String password = userPassword.getText().toString();
                /**
                 * user code inconsistent
                 */
                if(!password.equals(userPasswordRepeat.getText().toString())){
                    Toast.makeText(SignupAty.this,"password inconsistency",Toast.LENGTH_SHORT).show();
                }
                else{
                    AVUser user = new AVUser();
                    user.setUsername(username);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(AVException e) {
                            if (e == null) {
                                Toast.makeText(SignupAty.this,"sign up successfully",Toast.LENGTH_SHORT).show();
                                entrance();
                            } else {
                                // failed
                            }
                        }
                    });

                }



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

    public void entrance(){
        Intent intent = new Intent(SignupAty.this,ShowPage.class);
        startActivity(intent);
        finish();
    }

}
