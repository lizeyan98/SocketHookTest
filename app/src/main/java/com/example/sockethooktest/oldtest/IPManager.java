package com.example.sockethooktest.oldtest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class IPManager {
    private Map<SocketAddress, SocketAddress> real2MockSocketAddressMap = new HashMap<>();

    public void setIpAvailable(SocketAddress realIp){
        for (Map.Entry<SocketAddress, SocketAddress> entry: real2MockSocketAddressMap.entrySet()){
            if (entry.getKey().equals(realIp)){

            }
        }
    }

    public String getMockIp(){
        //获得该网段的所有ip，去除网关ip与本机真实ip
        String localIp = getLocalIPAddress();
        if (localIp == null){
           return null;
        }
        String[] address = localIp.split("\\.");
        return null;
    }

    public String getLocalSocketAddress() throws IOException {
        String string = "127.0.0.1" +
                getAvailableLocalPort();
        return string;
    }

    public int getAvailableLocalPort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        return serverSocket.getLocalPort();

    }

    private static String getLocalIPAddress(){
        try {
            Enumeration<NetworkInterface> allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (allNetworkInterfaces == null){
                return null;
            }
            while(allNetworkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface=allNetworkInterfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()
                        || networkInterface.getDisplayName().contains("VM")){
                    continue;
                }

                for(Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                    inetAddressEnumeration.hasMoreElements();){
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    if (inetAddress != null){
                        if (inetAddress.getAddress().length == 4){
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }

        }catch (SocketException e){
            e.printStackTrace();
        }
        return null;
    }

}
