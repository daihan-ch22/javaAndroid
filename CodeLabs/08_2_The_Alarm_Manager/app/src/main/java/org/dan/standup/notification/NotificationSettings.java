package org.dan.standup.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import org.dan.standup.GLOBAL_CODE;

/**
 * THIS CLASS HANDLES GENERAL NOTIFICATION SETTINGS
 */
public class NotificationSettings implements Notifiable {

    //VAR INIT
    private NotificationManager mNotificationManager;
    private Context context;
    public static final int NOTIFICATION_ID = 0;


    // INIT BY CONSTRUCTOR - Context from MainActivity
    public NotificationSettings(Context context) {
        this.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    // CHANNEL SETTING
    /** NotificationChannel is only available from Oreo(API 16) or higher **/
    @Override
    public void createNotificationChannel(){
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel notificationChannel = new NotificationChannel(
                        GLOBAL_CODE.PRIMARY.code,
                        "Stand Up Notification",
                        NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");

                mNotificationManager.createNotificationChannel(notificationChannel);
            }
    }

    //CANCEL ALL NOTIFICATION
    @Override
    public void cancelNotification(){
        mNotificationManager.cancelAll();
    }




}
