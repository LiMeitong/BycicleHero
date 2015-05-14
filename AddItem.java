package com.course.byciclehero;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

/**
 * add an item to server -- LostItem / SellItem
 */
public class AddItem extends ActionBarActivity {

    public ImageButton chooseImage;
    public Converter converter;
    public Bitmap changedBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Button sellBtn = (Button) findViewById(R.id.sellPostBtn);
        Button lostBtn = (Button) findViewById(R.id.lostPostBtn);
        changedBitmap = null;
        /**
         * convert drawable to byte[]
         */
        converter = new Converter();

        /**
         * add sell item to server
         */
        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final EditText rewardEditText = (EditText) findViewById(R.id.rewardInput);
                final EditText descriptionEditText = (EditText) findViewById(R.id.descriptInput);

                /**
                 * create a thread to upload in baclground
                 */
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * do not use default image
                         */
                        AVObject sellItem = new AVObject("SellItem");
                        AVFile avFile = null;
                        if( changedBitmap != null ){
                            avFile = new AVFile("sellItemPicture",converter.bitmapToByte(changedBitmap));
                            try {
                                avFile.save();
                                /**
                                 * create SellItem on server
                                 */
                                sellItem.put("UserID", AVUser.getCurrentUser()); // pointer for AVuser
                                sellItem.put("ImageFile",avFile); // pointer for file
                                sellItem.put("DescriptionString",descriptionEditText.getText().toString());
                                sellItem.put("Price",rewardEditText.getText().toString());
                                sellItem.save();
                                Log.d("err","successful save.");
                            } catch (AVException e) {
                                Log.d( "err","Av file save failed." );
                            }
                        }

                    }
                });

                thread.start();



                setResult(10);
                finish();
            }
        });
        lostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText rewardEditText = (EditText) findViewById(R.id.rewardInput);
                final EditText descriptionEditText = (EditText) findViewById(R.id.descriptInput);

                /**
                 * create a thread to upload in baclground
                 */
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * do not use default image
                         */
                        AVObject sellItem = new AVObject("LostItem");
                        AVFile avFile = null;
                        if( changedBitmap != null ){
                            avFile = new AVFile("lostItemPicture",converter.bitmapToByte(changedBitmap));
                            try {
                                avFile.save();
                                /**
                                 * create SellItem on server
                                 */
                                sellItem.put("UserID", AVUser.getCurrentUser()); // pointer for Avuser
                                sellItem.put("ImageFile",avFile); // pointer for file
                                sellItem.put("DescriptionString",descriptionEditText.getText().toString());
                                sellItem.put("Reward",rewardEditText.getText().toString());
                                sellItem.save();
                                Log.d("err","successful save.");
                            } catch (AVException e) {
                                Log.d( "err","Av file save failed." );
                            }
                        }

                    }
                });

                thread.start();

                setResult(10);
                finish();
            }
        });

        chooseImage = (ImageButton) findViewById(R.id.chooseImageButton);
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * no camera involved, only pick up existing photos
                 */
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            /**
             * picked up a photo
             */
            case 100:
                if( data == null )
                    break;
                Intent intent =  ProcessPhoto.startPhotoZoom(data.getData());
                startActivityForResult(intent, 300);
                break;
            case 300:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        changedBitmap = big(photo);
                        chooseImage.setImageBitmap(changedBitmap);
                    }
                }
                break;
            default:
                break;
        }

    }
    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(2.3f,2.3f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
