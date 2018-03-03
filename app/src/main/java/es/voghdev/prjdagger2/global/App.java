/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.global;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import es.voghdev.prjdagger2.ui.picasso.PicassoImageCache;

public class App extends Application implements HasActivityInjector {
    public static final String IMAGES_DIR = "images";

    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    private PicassoImageCache cache;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
        initializeImageCache();
    }

    private void initializeDependencyInjector() {
        //DaggerApplicationComponent.create().inject(this);
    }

    public void initializeImageCache() {
        File cacheDir = getPicturesDir();
        if (cacheDir == null) {
            return;
        }

        cacheDir.mkdirs();
        cache = new PicassoImageCache(this, cacheDir);
        Picasso picasso = new Picasso.Builder(this)
                //        .downloader(new OkHttpDownloader(cacheDir))
                .memoryCache(cache)
                .build();
        //Picasso.setSingletonInstance(picasso);
    }

//    public MainModule getMainModule() {
//        return mainModule;
//    }

    public File getPicturesDir() {
        File f = getExternalFilesDir(IMAGES_DIR);
        return f;
    }

//    public RootComponent getComponent() {
//        return component;
//    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
