package org.dan.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    // 서비스는 한번 실행해도 계속 떠있음.
    // 그래서 새로 만들어도 이미 만들어진 것이 있기 때문에 만들지 않음.
    // -> onCreate()쪽 로그는 한번만 나옴
    //
    // 서비스는 intent로 넘어온 데이터를 확인할때 onStartCommand로 받아서 처리할 수 있다.
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "called onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "called onStart()");

        if(intent == null){
            return Service.START_STICKY; //서비스가 종료되도 계속 실행해달라
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "Data Received : " + command + ", " + name);

        try {
            Thread.sleep(5000);
        } catch (Exception e){

        }

        /* 서비스에서 메인액티비티로 데이터 전달*/
        // 화면이 없는데서 화면쪽으로 화면을 띄워달라고 하면 에러생김 (화면은 Task라는 것으로 묶여있음 - 각각의 앱끼리 필요시 서로 띄워줄 수 있도록)
        // 테스크에 정보가 없다 -> 옵션을 줘야한다 -> Flags
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  //일반적으로 이렇게 3개의 flags를 자주 사용함
                            Intent.FLAG_ACTIVITY_SINGLE_TOP | //만들어져 있는 액티비티면 그걸 재활용 해주세요
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 위에 다른 화면이 있으면 그걸 제거

        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name);
        startActivity(showIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "called onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) { //onBind()는 잘 사용하지 않음. 안쓰고도 할 수 있어서
        throw new UnsupportedOperationException("Not yet implemented");
    }
}