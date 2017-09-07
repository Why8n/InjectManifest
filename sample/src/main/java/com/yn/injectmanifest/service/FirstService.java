package com.yn.injectmanifest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectService;

/**
 * Created by Whyn on 2017/9/5.
 */

@InjectService(
        name = ".FirstService",
        label = "Inject Service test",
        intentFilter = @InjectIntentFilter(
                action = "com.yn.action.FirstService",
                category = "com.yn.category.serviceTest")
)
public class FirstService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
