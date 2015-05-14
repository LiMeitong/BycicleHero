package com.course.byciclehero;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by lmt on 15/5/14.
 */
public class Converter {


    public  byte[] bitmapToByte(Bitmap draw){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        draw.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public Bitmap byteToBitmap( byte[] fileData ){
        Bitmap image = null;
        image =  BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
        return image;
    }

}
