package com.example.sockethooktest.oldtest;


import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;


public class SocketHookFactory implements SocketImplFactory
{
    public static String TAG = "SockeetHookFactory";
    private static Boolean isHook = false;
    private static Constructor socketConstructor = null;



    public static void setHook(Boolean set){
        isHook = set;
    }


    public static void initSocket() {
        if ( socketConstructor != null ) { return; }

        Socket  socket = new Socket();
        try{
            Field implField = Socket.class.getDeclaredField("impl");
            implField.setAccessible( true );
            Class<?> clazz = implField.get(socket).getClass();
            SocketHookImpl.initSocketImpl(clazz);
            socketConstructor = clazz.getDeclaredConstructor();
            socketConstructor.setAccessible(true);
        }catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e){
            throw new RuntimeException("SocketHookFactory init failed!");
        }

        try {
            socket.close();
        }
        catch ( IOException ignored)
        {
            ignored.printStackTrace();
        }
    }

    public SocketImpl createSocketImpl() {

        if(isHook) {
            try {

                return new SocketHookImpl(socketConstructor);
            } catch (Exception e) {
                try {
                    return (SocketImpl) socketConstructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            try {
                return (SocketImpl) socketConstructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
