package cn.cssf.chapter8.call;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import cn.cssf.chapter8.R;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        findViewById(R.id.call_listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
