package com.dl7.mvp;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.dl7.mvp.api.RetrofitService;
import com.dl7.mvp.injector.components.ApplicationComponent;
import com.dl7.mvp.injector.components.DaggerApplicationComponent;
import com.dl7.mvp.injector.modules.ApplicationModule;
import com.dl7.mvp.local.table.DaoMaster;
import com.dl7.mvp.local.table.DaoSession;
import com.dl7.mvp.rxbus.RxBus;
import com.dl7.mvp.utils.CrashHandler;
import com.dl7.mvp.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import static com.antfortune.freeline.FreelineCore.getApplication;

/**
 * Created by moligy on 2017/7/18.
 * Application
 */

public class AndroidApplication extends Application {

    private static final String DB_NAME = "m-db";

    private static ApplicationComponent sAppComponent;
    private static Context sContext;
    //private DaoSession mDaoSession;
    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();

    private DaoSession mDaoSession;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    public Context getAndroidApplication(){
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = AndroidApplication.this;
        _initDatabase();
        _initInjector();
        _initConfig();
    }

    /*public Context getApplication(){
        return sContext;
    }*/
    /**
     * 使用Tinker生成Application，这里改成静态调用
     * @return
     */
    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    public static Context getContext() {
        return sContext;
    }



    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, mDaoSession, mRxBus))
                .build();
    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    /**
     * 初始化配置
     */
    private void _initConfig() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(getApplication());
            Logger.init("LogTAG");
        }
        CrashHandler.getInstance().init(getApplication());
        RetrofitService.init();
        ToastUtils.init(getApplication());
    }
}
