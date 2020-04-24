package cn.cssf.chapter7;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}