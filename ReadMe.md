[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

InjectManifest
----------------
[**中文文档**](http://www.jianshu.com/p/3c1efdfe43e6)

using annotations to generate elements into AndroidManifest.xml
* Eliminate manually register components in AndroidManifest.xml 
* using annotation processor and custom gradle plugin to complete generating process,absolutely no effect on Runtime.
* completely annotatios,without even a single code.
* support merging original AndroidManifest.xml elements with which you annotated.
* support components full configurations by annotations.

Usage
---------------------------
* `manifest` tag:
```Java
@InjectManifest(
        pkName = "com.yn.injectmanifest",
        installLocation = INTERNAL_ONLY,
        sharedUserId = "android.uid.system"
)
public class App extends Application {
}
```
rebuild and then you can get:
![@InjectManifest](http://upload-images.jianshu.io/upload_images/2222997-a0b13c8078301a2e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
other configurations for `manifest` tag are all supported by `@InjectManifes`

* `application` tag 
```Java
@InjectApp(
        name = ".App", //you can full class name or just simply using a .classSimpleName
        label = "i am app",
        debuggable = TRUE,
        metaData = @InjectMetaData(name = "app/meta-data")
)
public class App extends Application {
}
```
rebuild and then you can get:
![@InjectApp](http://upload-images.jianshu.io/upload_images/2222997-6374cdd4041aab8b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `application` tag are all supported by `@InjectApp`

* `activity` tag:
```Java
@InjectActivity(
        name = ".MainActivity",
        intentFilter = @InjectIntentFilter(
                action = {"android.intent.action.MAIN", "android.intent.action_whyn_test"},
                category = {"android.intent.category.LAUNCHER", "android.intent.category.whyn"},
                data = @InjectData(mimeType = "image/*")
        ))
public class MainActivity extends AppCompatActivity {}
```
rebuild and then you can get:
![@InjectActivity](http://upload-images.jianshu.io/upload_images/2222997-3e94140c217b939f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `activity` tag are all supported by `@InjectActivity`

* `service` tag:
```java
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
public class FirstService extends Service {···}
```
rebuild and then you can get:
![@InjectService](http://upload-images.jianshu.io/upload_images/2222997-86f2e79b8d7c04e5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `service` tag are all supported by `@InjectService`

* `receiver` tag:
```java
@InjectReceiver(
        name = ".FirstReceiver",
        label = "hi i am first receiver",
        process = ".remote",
        enabled = TRUE
)
public class FirstReceiver extends BroadcastReceiver {···}
```
rebuild and then you can get:
![@InjectReceiver](http://upload-images.jianshu.io/upload_images/2222997-b61420b48ef9e3b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `receiver` tag are all supported by `@InjectReceiver`

* `provider` tag:
```java
@InjectProvider(
        authorities = "com.yn.authorities",
        name = ".FirstProvider",
        label = "I am ContentProvider"
)
public class FirstProvider extends android.content.ContentProvider {···}
```
rebuild and then you can get:
![@InjectProvider](http://upload-images.jianshu.io/upload_images/2222997-de7c2958c0c85541.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `provider` tag are all supported by `@InjectProvider`

* `uses-permission` tag:
```java
@InjectUsesPermission({
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_WIFI_STATE,
    })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
```
rebuild and then you can get:
![@InjectUsesPermission](http://upload-images.jianshu.io/upload_images/2222997-a9af8b6712eeafb7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

other configurations for `uses-permission` tag are all supported by `@InjectUsesPermission`

Download
--------
Via Gradle:
first add the plugin to your `buildscript`:
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.whyn:injectmanifest-plugin:1.1.0'
    }
}
```
and then apply it in your module:
```groovy
apply plugin: 'com.android.application'
apply plugin: 'com.whyn.plugin.injectmanifest'
```

Notice
--------
* currently **InjectManifest** only supports tags: `manifest`,`application`,`activity`,`service`,`receiver`,`provider`,`uses-permission`,so if your original AndroidManifest.xml contains any other tags,they won't be kept.
* **InjectManifest** will replace the original AndroidManifest.xml to the generated one,and before that,the original one will be rename to AndroidManifest_old.xml,so whenever you lost something in the generated file,you can always get back from the backup AndroidManifest_old.xml.
* if you need to pass arguments to annotaton processor in your project,please remember to append to it,not to replace,otherwise,the default arguments passed by **InjectManifest** will get lost,so merging will no effect.
```groovy
android {
    defaultConfig{
    ···
    ···
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += [xxxxx: 'yyyyy'] 
            }
        }
    }
}
```
* if you have changed the default path of the AndroidManifest.xml,you have to tell the annoation processor,otherwise,merge will not happen.
```
android {
    defaultConfig{
    ···
    ···
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AndroidManifestPath: android.sourceSets.main.manifest.srcFile.absolutePath] 
            }
        }
    }
}
```
* if you have modified the default generated source file path,or if you don't wanna backup AndroidManifest.xml,you can add the fllowings:
```groovy
//app build.gradle
manifestConfig {
    //the defautl AndroidManifest.xml path
    originManifestPath android.sourceSets.main.manifest.srcFile.absolutePath

    //the AndroidManifest.xml path generated by annotation processor
    genManifestPath "$project.buildDir/generated/source/apt/debug/Collections.xml"
    
    //to save the original AndroidManifest: true -- save,false -- not save
    saveOrigin false
}
```

License
-------

    Copyright 2017 Whyn

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
