package org.dan.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import org.dan.notifyme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements buttonListners{

    private static final String PRIMARY_CHANNEL_ID = "Primary_Notification_Channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;
    private ActivityMainBinding binding;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "org.dan.notifyme.ACTION_UPDATE_NOTIFICATION";
    private NotificationReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mReceiver = new NotificationReceiver(this.getApplicationContext(), this );

        buttonListner();
        createNotificationChannel();
        setNotificationButtonState(true, false, false);
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    /** CREATES NOTIFICATION CHANNEL **/
    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel =
                    new NotificationChannel(PRIMARY_CHANNEL_ID,
                            "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            getNotificationBuilder().setDefaults(NotificationCompat.DEFAULT_ALL);
            notificationChannel.setDescription("Notification From Mascot");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    /** NOTIFICATION BUILDER - MAKING NOTIFICATION HERE **/
    private NotificationCompat.Builder getNotificationBuilder() {

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
        notifyBuilder.setContentTitle("You've been notified!");
        notifyBuilder.setContentText("This is your notification text");
        notifyBuilder.setSmallIcon(R.drawable.ic_android);
        notifyBuilder.setContentIntent(notificationPendingIntent);
        notifyBuilder.setAutoCancel(true); //알림시 해당 알림 종료
        notifyBuilder.setDeleteIntent(notificationPendingIntent);

        return notifyBuilder;
    }

    /** NOTIFICATION ACTIONS **/
    @Override
    public void sendNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);

        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID,
                updateIntent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent);
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, true, true);
    }
    @Override /** UPDATE **/
    public void updateNotification() {
        //Convert Image into Bitmap Format
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        /*notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()  //style for notification
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!"));*/
        notifyBuilder.setStyle(new NotificationCompat.InboxStyle()
                        .setBigContentTitle("InboxStyleTitle")
                                .setSummaryText("test"));
        notifyBuilder.setContentText("UPDATED~");
        notifyBuilder.setContentTitle("This is your Updated Notification :) ");
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());  //스타일 변경한것 빌드해서 알림처리
        setNotificationButtonState(false, false, true);
    }
    @Override /** CANCEL **/
    public void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
    }


    /** BUTTON EVENTS **/
    private void buttonListner() {
        binding.notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });
        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
    }

    /** SET BUTTON STATES BASED ON NOTIFICATION STATUS **/
    private void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled) {
        binding.notify.setEnabled(isNotifyEnabled);
        binding.update.setEnabled(isUpdateEnabled);
        binding.cancel.setEnabled(isCancelEnabled);
    }
}