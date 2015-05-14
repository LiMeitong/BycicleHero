package com.course.byciclehero;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DetailAty extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aty);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button chatButton = (Button) findViewById(R.id.chatBtn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO pass item ID
                startActivityForResult(new Intent(DetailAty.this, ChatSingle.class),666);
                Toast.makeText(DetailAty.this,"chatwith",Toast.LENGTH_SHORT).show();
            }
        });

        TextView descriptionText = (TextView) findViewById(R.id.descriptTextview);
        TextView rewardText = (TextView) findViewById(R.id.rewardTextview);

        Bundle getDataTransformed = getIntent().getExtras();
        String descript = getDataTransformed.getString("descript");
        String priceOrReward = getDataTransformed.getString("priceOrReward");


        descriptionText.setText(descript);
        rewardText.setText(priceOrReward);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if( id == android.R.id.home ){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        finish();
    }

}
