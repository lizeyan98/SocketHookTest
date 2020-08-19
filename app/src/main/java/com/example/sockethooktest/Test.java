package com.example.sockethooktest;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static String TAG = "Test";
    public void test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(9999);
                    Socket socket = serverSocket.accept();
                    Log.d(TAG, "accept for port 9999, socket:" + socket);
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG,"get socket");

                    Socket socket = new Socket("127.0.0.1",9999);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void test2(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ServerSocket serverSocket = new ServerSocket(9999);
//                    Socket socket = serverSocket.accept();
//                    Log.d(TAG, "accept for port 9998: "+socket.toString());
//                    InputStream inputStream = socket.getInputStream();
//                    DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//                    Log.d(TAG,"MSG: " + dataInputStream.readUTF());
//                    dataInputStream.close();
//                    inputStream.close();
//                    serverSocket.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG,"get socket");
//                    while(true){
                        Socket socket = new Socket("10.1.1.1",9999);
                        Log.d(TAG,socket.toString());


                        OutputStream outputStream = socket.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        dataOutputStream.writeUTF("helllllllo");
                        dataOutputStream.close();
                        outputStream.close();
                        socket.close();
//                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
