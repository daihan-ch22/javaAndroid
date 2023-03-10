package org.dan.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + " .ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String intentAction = intent.getAction();
        if(intentAction != null){
            String toastMSG = "unknown intent action";
            switch(intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMSG = "Power Connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMSG = "Power Disconnected";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMSG = "Custom Broadcast Received";
                    //callAlert(context);
                    break;
                case "COUNTS" :
                    toastMSG = "Custom BroadCast Received with Num is = " + intent.getStringExtra("wow").toString();
                default: break;
            }
            Toast.makeText(context, toastMSG, Toast.LENGTH_LONG).show();
        }

    }
    /*private void callAlert(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("wow");
        builder.setMessage("asdasd");
        builder.show();
    }*/
}