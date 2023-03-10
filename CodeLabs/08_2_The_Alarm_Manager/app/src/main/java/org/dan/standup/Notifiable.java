package org.dan.standup;

import android.content.Context;

interface Notifiable {

    void cancelNotification();

    void deliverNotification(Context context);

    void createNotificationChannel();

}
