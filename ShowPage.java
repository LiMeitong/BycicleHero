package com.course.byciclehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.course.byciclehero.frament.MyFrament1;
import com.course.byciclehero.frament.MyFrament2;
import com.course.byciclehero.frament.MyFrament3;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ShowPage extends ActionBarActivity {


    private RelativeLayout RelativeLayout1, RelativeLayout2, RelativeLayout3;
    private TextView textView1, textView2, textView3;
    private ViewPager viewPager;
    private int[] selectList;
    private TextView[] textViewList;
    private int selectID = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_page);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.show();

        initLayout();
        initData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_show_page, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addItem:
                Intent intent = new Intent(this,AddItem.class);
                startActivityForResult(intent,10);
                return true;
            case R.id.searchItem:
                IntentIntegrator integrator = new IntentIntegrator(ShowPage.this);
                integrator.initiateScan();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /**
         * get QR code
         */
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String re = scanResult.getContents();
            Toast.makeText(ShowPage.this,re,Toast.LENGTH_SHORT).show();
        }

        switch (requestCode){
            /**
             * hint for sent message
             */
            case  10:
                Toast.makeText(this," Posted !",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initLayout() {
        RelativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        RelativeLayout2 = (RelativeLayout) findViewById(R.id.RelativeLayout2);
        RelativeLayout3 = (RelativeLayout) findViewById(R.id.RelativeLayout3);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initData() {
        selectList = new int[] { 0, 1, 1 };
        textViewList = new TextView[] { textView1, textView2, textView3 };

        RelativeLayout1.setOnClickListener(listener);
        RelativeLayout2.setOnClickListener(listener);
        RelativeLayout3.setOnClickListener(listener);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(changeListener);
    }

    private OnClickListener listener = new OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.RelativeLayout1:
                    if (selectID == 0) {
                        return;
                    } else {
                        setSelectedTitle(0);
                        viewPager.setCurrentItem(0);
                    }
                    break;
                case R.id.RelativeLayout2:
                    if (selectID == 1) {
                        return;
                    } else {
                        setSelectedTitle(1);
                        viewPager.setCurrentItem(1);
                    }
                    break;
                case R.id.RelativeLayout3:
                    if (selectID == 2) {
                        return;
                    } else {
                        setSelectedTitle(2);
                        viewPager.setCurrentItem(2);
                    }
                    break;
            }
        }
    };



    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(
            getSupportFragmentManager()) {

        public int getCount() {
            return selectList.length;
        }

        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment=new MyFrament1();
                    break;
                case 1:
                    fragment=new MyFrament2();
                    break;
                case 2:
                    fragment=new MyFrament3();
                    break;
            }
            return fragment;
        }
    };

    private SimpleOnPageChangeListener changeListener=new SimpleOnPageChangeListener(){
        public void onPageSelected(int position) {
            setSelectedTitle(position);
        }
    };


    private void setSelectedTitle(int position) {
        for (int i = 0; i < selectList.length; i++) {
            if (selectList[i] == 0) {
                selectList[i] = 1;
                textViewList[i].setVisibility(View.INVISIBLE);
            }
        }
        selectList[position] = 0;
        textViewList[position].setVisibility(View.VISIBLE);
        selectID = position;
    }
    public void startChat(){
        Intent chatIntent = new Intent(this,ChatSingle.class);
        startActivity(chatIntent);

    }
}
