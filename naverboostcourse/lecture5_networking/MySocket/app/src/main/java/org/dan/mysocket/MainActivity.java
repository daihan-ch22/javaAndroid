package org.dan.mysocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText input1;
    TextView output1;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button button = findViewById(R.id.sendButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = input1.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();

            }
        });

        Button startServerButton = findViewById(R.id.button3);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    public void startServer(){
        int port = 8083;

        try {
            ServerSocket server = new ServerSocket(port);

            while(true){
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                println("Connected with Client : " + clientHost + "," + clientPort);

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String input = instream.readObject().toString();
                println("Data received : " + input);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(input + " from server..");
                outstream.flush();
                println("Data Sent");
                sock.close();
            }
        } catch (Exception e) {

        }
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                output1.append(data + "\n");
            }
        });
    }


    public void send(String data){
        int port = 8083;

        try {
            Socket socket = new Socket("localhost", port);

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(data);
            outStream.flush();

            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            String input = inStream.readObject().toString();

            socket.close();


        } catch (Exception e) {
        }

    }
}