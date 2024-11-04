package com.Network.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class Server1{

    public static void test(){
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(1234);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String message = new String(receivePacket.getData());
            System.out.println("Received: " + message.trim());
            serverSocket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

public class BasicUdpServer {
    public static void main(String[] args){
        Server1.test();
    }
}
