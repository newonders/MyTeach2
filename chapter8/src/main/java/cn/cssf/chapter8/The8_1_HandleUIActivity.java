package cn.cssf.chapter8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class The8_1_HandleUIActivity extends AppCompatActivity {

    private Handler handler;
    private TextView textView;
    private final static int UPDATE_TEXT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler();
        setContentView(R.layout.activity_thread_ui);
        textView = findViewById(R.id.threadTv);
        Button button = findViewById(R.id.threadBt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg){
            if (msg.what == UPDATE_TEXT){
                textView.setText("更新后");
            }
        }

    }
}


