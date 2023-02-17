package org.dan.mysmsreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);//권한 체크

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Granteed Permission of Receiving SMS", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(getApplicationContext(), "No Permission has granteed.", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) //권한에 대한 설명이 필요한지??
            {
                Toast.makeText(this, "Required SMS Receive Permission.", Toast.LENGTH_LONG).show();
            } else {
                //권한 달라고 요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 101);
            }
        }
    }

    //권한 부여후 결과를 얻기 위한 콜백함수
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 101:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "SMS Permission Granted by User :)", Toast.LENGTH_LONG).show();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "SMS Permission Denied by User :(", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "SMS Permission has been rejected :/", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }
}