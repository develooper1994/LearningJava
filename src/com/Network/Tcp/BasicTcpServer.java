package com.Network.Tcp;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static java.lang.System.out;

class Server1{

    public static void test(){
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            out.println("Server is listing on port 1234");
            Socket socket = serverSocket.accept();
            out.println("Client connected");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // (stream, autoFlush)
            out.println("Hello World!");
            // close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
*
* */
class EchoServer implements Runnable{
    private ServerSocket serverSocket;

    public EchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10*1000); // 10 sn
    }

    @Override
    public void run() {
        for (;;) {
            try {
                out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket client = serverSocket.accept();
                out.println("Just connect to " + client.getRemoteSocketAddress());

                // read from client socket
                DataInputStream input = new DataInputStream(client.getInputStream());
                String data = input.readUTF();
                out.println(data);

                // write to client socket
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                output.writeUTF(data);

                // close connection
                client.close();

            } catch (SocketTimeoutException e) {
                out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void test(){
        int port = 1234;
        EchoServer echoServer = null;
        try {
            echoServer = new EchoServer(port);
            echoServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class BasicTcpServer {
    public static void main(String[] args){
//        Server1.test();
        EchoServer.test();
    }
}
