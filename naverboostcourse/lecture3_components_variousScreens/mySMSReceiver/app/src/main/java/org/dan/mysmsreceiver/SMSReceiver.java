package org.dan.mysmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = "smsReceiver";

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d(TAG, "onReceive() has called.");
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSMSMessage(bundle);

        if(messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "sender : " + sender);

            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "contents : "  + contents);

            Date receivedDate = new Date( messages[0].getTimestampMillis() );
            Log.d(TAG,"received date : " + receivedDate);

            sendToActivity(context,sender, contents, receivedDate);
        }


    }

    private void sendToActivity(Context context, String sender, String content, Date receivedDate){
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("contents", content);
        intent.putExtra("receivedDate", format.format(receivedDate));

        context.startActivity(intent);

    }

    private SmsMessage[] parseSMSMessage(Bundle bundle) {
        /* SMS데이터를 처리하는 국제 표준 프로토콜 - smpp 이라는게 있는데
            그 안에 데이터가 pdus라는 이름으로 들어가있음.
            pdus안에 SMS와 관련된 데이터들이 들어있음.*/

        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];

        for (int i = 0; i < objects.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //마시멜로 버전 기점으로 변경점
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], format);
            }
            else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
        }
        return messages;
    }
}