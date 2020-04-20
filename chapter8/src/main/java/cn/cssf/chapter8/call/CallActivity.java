package cn.cssf.chapter8.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import cn.cssf.chapter8.R;

public class CallActivity extends AppCompatActivity {

    private final static String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    private final static int REQUEST_CALL = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        findViewById(R.id.call_listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkSelfPermission(CallActivity.PERMISSION_CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED){
                    doIntent();
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }
            }
        });
    }

    private void doIntent(){
        Intent intent = new Intent(CallActivity.this,
                PhoneListenService.class);
        intent.setAction(PhoneListenService.ACTION_REGISTER_LISTENER);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            doIntent();
        }
    }
}
