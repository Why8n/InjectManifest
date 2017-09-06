package com.yn.injectmanifest;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yn.annotations.InjectActivity;
import com.yn.annotations.InjectIntentFilter;
import com.yn.annotations.InjectPermissions;

import java.util.ArrayList;
import java.util.List;

@InjectPermissions({
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
})
@InjectActivity(
        name= "com.yn.injectmanifest.MainActivity",
        intentFilter = @InjectIntentFilter(
                action = {"android.intent.action.MAIN", "android.intent.action_whyn_test"},
                category = "android.intent.category.LAUNCHER"
        ))
public class MainActivity extends AppCompatActivity {
    @InjectPermissions(Manifest.permission.SEND_SMS)
    String field1;

    @InjectPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @InjectPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
    public void onStartActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
        test(new Son());
    }


    public <T extends Dad> void test(T item) {
        List<T> list = new ArrayList<>();
//        list.add(new Son());
        list.add(item);
    }


    private static class Dad {

    }

    private static class Son extends Dad {

    }
}



