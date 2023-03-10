package org.dan.standup.notification;

import android.content.Context;

interface Notifiable {

    void cancelNotification();

    //@Deprecated
    //void deliverNotification(Context context);

    void createNotificationChannel();

}
