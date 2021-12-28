package com.joseamaro.breeddog.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this
       // Realm.init(this)
        //  Realm.setDefaultConfiguration(getRealmConfiguration());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    protected override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        /*public static RealmConfiguration getRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name(Bitmap.Config.DB_NAME + ".realm")
                .schemaVersion(0)
                .encryptionKey(new byte[64])
                .deleteRealmIfMigrationNeeded()
                .build();
    }*/
        @SuppressLint("StaticFieldLeak")
        var instance: Context? = null
            private set
    }
}