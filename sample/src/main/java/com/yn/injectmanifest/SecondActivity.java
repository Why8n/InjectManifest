package com.yn.injectmanifest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yn.annotations.InjectActivity;
import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectUsesPermission;
import com.yn.annotations.InjectUsesPermissionDetail;


/**
 * Created by Whyn on 2017/8/31.
 */

@InjectActivity(name = "com.yn.injectmanifest.SecondActivity",
        label = "I'm Second Activity",
        intentFilter = @InjectIntentFilter(
//                action = {"com.yn.test"},
                category = "com.yn.category"
        ))
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }


    @InjectUsesPermission(
            permission = {
                    @InjectUsesPermissionDetail(permission = "com.permission.test01", maxSdkVersion = "13"),
                    @InjectUsesPermissionDetail(permission = "android.permission.BLUETOOTH", maxSdkVersion = "15")
            }
    )
    private void testPermission() {
    }
}
