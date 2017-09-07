package com.yn.injectmanifest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yn.annotations.InjectData;
import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectMetaData;
import com.yn.annotations.InjectService;

import static com.yn.annotations.enums.Correct.TRUE;

/**
 * Created by Whyn on 2017/9/5.
 */

@InjectService(
        enabled = TRUE,
        name = ".FirstService",
        label = "Inject Service test",
        intentFilter = @InjectIntentFilter(
                action = "com.yn.action.FirstService",
                category = "com.yn.category.serviceTest",
                data = @InjectData(
                        host = "sdcard",
                        mimeType = "video/mp4",
                        path = "/sdcard/1.MP4",
                        pathPattern = ".*\\.mp4",
                        pathPrefix = "/sdcard/",
                        port = "-2",
                        scheme = "file"
                )
        ),
        metaData = @InjectMetaData(name = "com.yn.meta-data.service")
)
public class FirstService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
