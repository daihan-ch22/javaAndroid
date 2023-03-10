package org.dan.standup;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * THIS CLASS HANDLES GENERAL NOTIFICATION SETTINGS
 */
public class NotificationSettings implements Notifiable {

    //VAR INIT
    private NotificationManager mNotificationManager;
    private Context context;
    private static final int NOTIFICATION_ID = 0;
    private enum CHANNEL_ID {
        PRIMARY("primary_notification_channel") ,
        SECONDARY("secondary_notification_channel");
        CHANNEL_ID(String channel_id) { this.channel_id = channel_id; }
        private final String channel_id;
    }

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
                        CHANNEL_ID.PRIMARY.channel_id,
                        "Stand Up Notification",
                        NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk");

                mNotificationManager.createNotificationChannel(notificationChannel);
            }
    }

    //SEND NOTIFICATION
    @Override
    public void deliverNotification(Context context){
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_ID,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID.PRIMARY.channel_id)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle("Stand Up Alert")
                .setContentText("You should stand up and move you butt!")
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    //CANCEL ALL NOTIFICATION
    @Override
    public void cancelNotification(){
        mNotificationManager.cancelAll();
    }



}
