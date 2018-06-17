package com.ashokvarma.androidx;

import android.app.Application;

import com.ashokvarma.androidx.util.ResourcesUtils;

/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 17/06/18
 */
public class XApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ResourcesUtils.initContext(getApplicationContext());
    }
}
