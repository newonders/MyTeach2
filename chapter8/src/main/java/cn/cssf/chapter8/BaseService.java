package cn.cssf.chapter8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BaseService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BaseService", "onCreate: BaseService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BaseService", "onStartCommand: BaseService");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BaseService", "onDestroy: BaseService");
    }
}
