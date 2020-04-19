package cn.cssf.chapter8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class The8_2_BindServiceActivity extends AppCompatActivity {

    private BindService.DownloadBinder downloadBinder;

    private Service bindService;

    private ServiceConnection connection = new ServiceConnection() {

        //这个过程是异步获取的
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (BindService.DownloadBinder)iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgessStatus();
            downloadBinder.stopDownload();
//            bindService = downloadBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) { }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);

        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(The8_2_BindServiceActivity.this,
                        BindService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });
    }
}
