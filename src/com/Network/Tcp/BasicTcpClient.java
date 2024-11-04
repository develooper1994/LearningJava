package com.Network.Tcp;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static java.lang.System.out;

class Client1{

    public static void test() {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Server says: " + in.readLine());
            // close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class EchoClient{

    public static void test(){
        String serverName = "localhost";
        int port = 1234;
        String data = "Echo Echo Echo...";
        out.println("Connecting to " + serverName + " on port " + port);
        try {
            Socket client = new Socket(serverName, port);
            out.println("Just connected to " + client.getRemoteSocketAddress() + " from " + client.getLocalAddress());

            if (!client.isConnected())
                return;

            // write to server socket
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.writeUTF(data);

            // read from server socket
            DataInputStream input = new DataInputStream(client.getInputStream());
            String echoData = input.readUTF();
            out.println("echoData: " + echoData);

            // close connection
            client.close();

        } catch (SocketException e) {
            System.err.println("Sunucu adresi bulunamadı: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

public class BasicTcpClient {

    public static void main(String[] args){
//        Client1.test();
        EchoClient.test();
    }
}
