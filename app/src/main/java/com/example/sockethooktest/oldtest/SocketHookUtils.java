package com.example.sockethooktest.oldtest;


import java.lang.reflect.Method;

public class SocketHookUtils {


    public static Method findMethod(Class<?> inputClazz, String findName ,Class<?>[] args){
        Class<?> temp=inputClazz;
        Method tmpMethod = null;
        while(temp!=null){
            try{
                tmpMethod = temp.getDeclaredMethod(findName,args);
                tmpMethod.setAccessible(true);
                return tmpMethod;
            }catch (NoSuchMethodException e){
                temp=temp.getSuperclass();
            }
        }
        return null;
    }

}