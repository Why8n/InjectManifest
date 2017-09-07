package com.yn.injectmanifest.App;

import android.app.Application;

import com.yn.annotations.InjectApp;
import com.yn.annotations.InjectManifest;

import static com.yn.annotations.InjectManifest.InstallLocation.INTERNAL_ONLY;

/**
 * Created by Whyn on 2017/9/6.
 */


@InjectManifest(
        installLocation = INTERNAL_ONLY,
        sharedUserId = "android.uid.system"
)
@InjectApp(name = ".App",label = "i am app")
public class App extends Application {
}
