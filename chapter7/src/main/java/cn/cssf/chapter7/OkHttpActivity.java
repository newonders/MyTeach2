package cn.cssf.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        responseText = findViewById(R.id.textViewOk);

        findViewById(R.id.okHttpBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithOkHttp();
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUtil.sendOkHttpRequest("http://www.baidu.com", new MyCallback());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }

    private class MyCallback implements okhttp3.Callback{

        @Override
        public void onFailure(Call call, IOException e) {
            //异常处理
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String responseStr = response.body().string();
            //只能在主线程更新，否则报错
//            responseText.setText(responseStr);
            showResponse(responseStr);
        }
    }
}
