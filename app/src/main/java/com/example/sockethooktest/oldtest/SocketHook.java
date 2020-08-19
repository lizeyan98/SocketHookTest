package com.example.sockethooktest.oldtest;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public class SocketHook {
    public static void startHook() throws IOException {
        SocketHookFactory.initSocket();
        SocketHookFactory.setHook(true);
        try{
            Socket.setSocketImplFactory(new SocketHookFactory());
//            ServerSocket.setSocketFactory(new SocketHookFactory());
        }catch (SocketException ignored){
        }
    }

    public static void stopHook(){
        SocketHookFactory.setHook(false);
    }
}
