package com.course.byciclehero;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by lmt on 15/5/8.
 */
public class BycicleApplication extends Application {
    @Override
    public void onCreate() {
        AVOSCloud.initialize(this, "zvqv3u8irntzvpwxu4x43dlsvsxaweomfr6y6vp4hof4bri8", "05npw6yfgep7una9vhhf0ysuqhch0a10dqr5v8f6x95i5w8q");
        super.onCreate();
    }
}
