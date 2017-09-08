package com.yn.injectmanifest.providers;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yn.annotations.InjectGrantUriPermission;
import com.yn.annotations.InjectPathPermission;
import com.yn.annotations.InjectProvider;

/**
 * Created by Whyn on 2017/9/8.
 */


@InjectProvider(
        authorities = "com.yn.authorities",
        name = ".FirstProvider",
        grantUriPermission = @InjectGrantUriPermission(
                path = "D:/",
                pathPrefix = "yn",
                pathPattern= "E:/"
        ),
        pathPermission = @InjectPathPermission(
                path = "string",
                pathPrefix = "string",
                pathPattern = "string",
                readPermission = "string",
                writePermission = "string",
                permission = "string"
        )

)
public class FirstProvider extends android.content.ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
