package cn.mozhx.quanxian;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    //用户已经授权，可以进行拍摄图片
                    Toast.makeText(MainActivity.this, "用户已经授权", Toast.LENGTH_SHORT).show();

                } else {
                    //申请授权
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //和之前的请求码比对
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户已经授权，可以执行操作了
                    if (android.Manifest.permission.CAMERA.equals(permissions[0])) {
                        //进行拍照
                        Toast.makeText(MainActivity.this, "进行拍照", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //授权失败，此处提示用户到设置页面打开权限
                    if (android.Manifest.permission.CAMERA.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(MainActivity.this, "请开启相机权限", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
}
