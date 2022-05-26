package sample.Server.src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class EchoMultiServer {

    private ServerSocket serverSocket;
    private int clientCount = 0;
    private Vector<EchoClientHandler> clientList = new Vector<>();
    private int id;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new EchoClientHandler(serverSocket.accept(), id++).start();
                clientCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public String getClients(){
        StringBuffer names = new StringBuffer();
        for (EchoClientHandler client : clientList)
            names.append("Client ").append(client.getClientId()).append("\n");
        return names.toString();
    }


    public void stop() {
        try {
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private DataOutputStream out;
        private DataInputStream in;
        private final int id;

        public EchoClientHandler(Socket socket, int id) {
            this.id = id;
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new DataInputStream(clientSocket.getInputStream());

                receiveFile();

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private  void receiveFile() throws IOException{
            int bytes;
            FileOutputStream fileOutputStream = new FileOutputStream("aboba.properties");

                // read file size
            byte[] buffer = new byte[4*1024];
            while ((bytes = in.read(buffer)) > 0) {
                fileOutputStream.write(buffer,0,bytes);
            }
            fileOutputStream.close();
        }

        protected int getClientId() {
            return id;
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(8000);
    }



}