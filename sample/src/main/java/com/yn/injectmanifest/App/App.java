package com.yn.injectmanifest.App;

import android.app.Application;

import com.yn.annotations.InjectApp;
import com.yn.annotations.InjectManifest;
import com.yn.annotations.InjectMetaData;

import static com.yn.annotations.InjectManifest.InstallLocation.INTERNAL_ONLY;
import static com.yn.annotations.enums.Correct.TRUE;

/**
 * Created by Whyn on 2017/9/6.
 */


@InjectManifest(
        pkName = "com.yn.injectmanifest",
        installLocation = INTERNAL_ONLY,
        sharedUserId = "android.uid.system"
)
@InjectApp(
        name = ".App", //you can full class name or just simply using a .classSimpleName
        label = "i am app",
        debuggable = TRUE,
        metaData = @InjectMetaData(name = "app/meta-data")
)
public class App extends Application {
}
