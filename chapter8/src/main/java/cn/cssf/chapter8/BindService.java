package cn.cssf.chapter8;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BindService extends Service {

    private IBinder mBinder;

    public BindService() {
        mBinder = new DownloadBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BindService", "onCreate: BindService");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("BindService", "onRebind: BindService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BindService", "onStartCommand: BindService");
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BindService", "onDestroy: BindService");
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Toast.makeText(BindService.this, "开始下载任务", Toast.LENGTH_SHORT).show();
        }
        public void stopDownload(){
            Toast.makeText(BindService.this, "中止下载任务", Toast.LENGTH_SHORT).show();
        }
        public int getProgessStatus(){
            Toast.makeText(BindService.this, "获取下载状态", Toast.LENGTH_SHORT).show();
            return 0;
        }

//        public Service getService(){
//            return BindService.this;
//        }
    }
}
