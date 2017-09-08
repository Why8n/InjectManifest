package com.yn.injectmanifest.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yn.annotations.InjectReceiver;

import static com.yn.annotations.enums.Correct.TRUE;

/**
 * Created by Whyn on 2017/9/5.
 */

@InjectReceiver(
        name = ".FirstReceiver",
        label = "hi i am first receiver",
        process = ".remote",
        enabled = TRUE
)

public class FirstReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
