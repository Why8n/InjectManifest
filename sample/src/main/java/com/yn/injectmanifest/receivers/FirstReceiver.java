package com.yn.injectmanifest.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yn.annotations.InjectMetaData;
import com.yn.annotations.InjectReceiver;

/**
 * Created by Whyn on 2017/9/5.
 */

@InjectReceiver(name = ".FirstReceiver", label = "hi i am first receiver",
        metaData = @InjectMetaData(resource = "receiver meta-data resource")

)

public class FirstReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
