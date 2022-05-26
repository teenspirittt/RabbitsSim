package sample.Server.src;

import sample.controller.PropertyPackage;

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

    public String getClients() {
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
        private ObjectOutputStream out;
        private DataInputStream in;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private int id;
        PropertyPackage prpPck;

        public EchoClientHandler(Socket socket, int id) {
            this.id = id;
            this.clientSocket = socket;
        }

        public void run() {
            try {
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ois = new ObjectInputStream(clientSocket.getInputStream());

                while (!clientSocket.isClosed() && !clientSocket.isOutputShutdown() && !clientSocket.isInputShutdown()) {
                    String code = ois.readUTF();
                    switch (code) {
                        case "SEND.PACKAGE" -> sendObj();
                        case "GET.PACKAGE" -> getObj();
                    }
                }

                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void getObj() {
            try {
                System.out.println("get props");
                prpPck = (PropertyPackage) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        public void sendObj() {
            try {
                System.out.println("send props");
                oos.writeObject(prpPck);
                oos.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        protected int getClientId() {
            return id;
        }

        private void receiveFile() throws IOException {
            int bytes;

            File file = new File("Client" + id + ".properties");

            file.createNewFile();


            byte[] buffer = new byte[4 * 1024];
            while ((bytes = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
                out.flush();
                buffer = new byte[4 * 1024];
            }
            in.reset();
        }

    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(8000);
    }


}