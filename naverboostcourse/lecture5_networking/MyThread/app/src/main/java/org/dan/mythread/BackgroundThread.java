package org.dan.mythread;

import android.os.Bundle;
import android.os.Message;

public class BackgroundThread extends Thread{
    int value;
    boolean running = false;
    ValueHandler handler;

    public BackgroundThread(ValueHandler handler){
        this.handler = handler;
    }

    public void run(){
        running = true;;
        while(running){
            value+=1;

            Message message = handler.obtainMessage();

            Bundle bundle = new Bundle();
            bundle.putInt("value", value);
            message.setData(bundle);

            handler.sendMessage(message);
            try {
                Thread.sleep(1000);
            } catch (Exception e){    }
        }
    }

    public int getValue() {
        return value;
    }
}
