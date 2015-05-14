package com.course.byciclehero;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ProcessPhoto {

    public static int photoNum = 0;
    private static Context context = null;

    public ProcessPhoto(Context _context){
        context = _context;
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri haha
     */
    public static Intent startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        return intent;
    }


}
