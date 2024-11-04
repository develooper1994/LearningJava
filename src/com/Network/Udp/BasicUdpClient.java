package com.Network.Udp;

import java.io.IOException;
import java.net.*;

class Client1{

    public static void test() {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            String message = "Hello, Server!";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1234);
            clientSocket.send(sendPacket);
            clientSocket.close();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

public class BasicUdpClient {

    public static void main(String[] args){
        Client1.test();
    }
}
