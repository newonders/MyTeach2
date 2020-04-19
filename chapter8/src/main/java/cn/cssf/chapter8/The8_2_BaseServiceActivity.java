package cn.cssf.chapter8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class The8_2_BaseServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_service);

        findViewById(R.id.serviceStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(The8_2_BaseServiceActivity.this,
                        BaseService.class);
                startService(intent);
            }
        });

        findViewById(R.id.serviceStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(The8_2_BaseServiceActivity.this,
                        BaseService.class);
                stopService(intent);
            }
        });

    }
}
