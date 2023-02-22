package org.dan.mythread;

import android.util.Log;

public class BackgroundThread extends Thread{

    int value = 0;
    boolean running = false;

    public void run(){
        running = true;

        while(running){
                value+=1;
                try {
                    Thread.sleep(1000);
                } catch (Exception e){    }
        }
    }

    public int getValue() {
        return value;
    }
}
