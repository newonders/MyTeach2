package cn.cssf.chapter6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PermissionAcitivity extends AppCompatActivity {

    private final int contactRequestCode = 10000; //通讯录权限
    private final int callRequestCode = 10001; //拨打电话权限
    private final int ExtRequestCode = 10002; //写外存储权限


    private final String[] permissions = new String[]{
        Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllGranted =
                        checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED;
                if (isAllGranted){
                    //执行需权限的代码
                    Toast.makeText(PermissionAcitivity.this, "可访问通信录",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PermissionAcitivity.this,
                            "拨打电话未授权", Toast.LENGTH_SHORT).show();
                    //申请权限
                    requestPermissions(permissions,contactRequestCode);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                boolean isAllGranted =
                        checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED;
                if (isAllGranted){
                    //执行需权限的代码
                    startActivity(intent);
                    Toast.makeText(PermissionAcitivity.this, "可拨打电话",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PermissionAcitivity.this,
                            "拨打电话未授权", Toast.LENGTH_SHORT).show();
                    //申请权限
                    requestPermissions(permissions,callRequestCode);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllGranted =
                        checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED;
                if (isAllGranted){
                    //执行需权限的代码
                    Toast.makeText(PermissionAcitivity.this, "可写外存储",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PermissionAcitivity.this,
                            "外存储未授权", Toast.LENGTH_SHORT).show();
                    //申请权限
                    requestPermissions(permissions,ExtRequestCode);

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.contactRequestCode){
            boolean isGranted = false;
            for (int grant : grantResults){
                if (grant != PackageManager.PERMISSION_GRANTED){
                    isGranted = false;
                    break;
                }
            }
            if (isGranted){
                //执行读取通信录代码
            }
        }
        else if (requestCode == this.callRequestCode){
            boolean isGranted = false;
            for (int grant : grantResults){
                if (grant != PackageManager.PERMISSION_GRANTED){
                    isGranted = false;
                    break;
                }
            }
            if (isGranted){
                //执行拨打电话代码
            }
        }
        else if (requestCode == this.ExtRequestCode){
            boolean isGranted = false;
            for (int grant : grantResults){
                if (grant != PackageManager.PERMISSION_GRANTED){
                    isGranted = false;
                    break;
                }
            }
            if (isGranted){
                //执行写外存储的代码
            }
        }
    }
}
