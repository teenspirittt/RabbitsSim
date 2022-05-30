package sample.Server.src;

import sample.controller.PropertyPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class EchoMultiServer {

    private static EchoMultiServer instance;
    private static int clientCount = 0;
    private static final Vector<EchoClientHandler> clientList = new Vector<>();
    private static int id = 0;

    public PropertyPackage prpPck;
    public PropertyPackage tmp;


    public static synchronized EchoMultiServer getInstance() {
        if (instance == null) {
            instance = new EchoMultiServer();
        }
        return instance;
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 8000;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                EchoClientHandler ech = new EchoClientHandler(serverSocket.accept(), id++);
                clientList.add(ech);
                clientCount++;

                ech.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop(serverSocket);
        }
    }

    public static String getClients() {
        StringBuilder names = new StringBuilder();
        for (EchoClientHandler client : clientList)
            names.append("Client ").append(client.getClientId()).append("\n");
        return names.toString();
    }


    public static void stop(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class EchoClientHandler extends Thread {
        private final Socket clientSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private final int id;
        EchoMultiServer ems = EchoMultiServer.getInstance();

        public EchoClientHandler(Socket socket, int id) {
            this.id = id;
            this.clientSocket = socket;
        }

        public void run() {
            try {
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ois = new ObjectInputStream(clientSocket.getInputStream());

                while (!clientSocket.isClosed() && !clientSocket.isOutputShutdown() && !clientSocket.isInputShutdown()) {
                    String request = ois.readUTF();
                    switch (request) {
                        case "SEND.PACKAGE" -> getObj();
                        case "GET.PACKAGE" -> sendObj();
                        case "GET.CLIENTS" -> sendClientsList();
                    }
                }
                oos.close();
                ois.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void getObj() {
            try {
                System.out.println("get props");
                ems.prpPck = (PropertyPackage) ois.readObject();
                ems.prpPck.printProperties();
                ems.tmp = ems.prpPck;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        public void sendObj() {
            try {
                System.out.println("send props");
                ems.tmp.printProperties();
                oos.writeObject(ems.tmp);
                oos.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendClientsList() {
            System.out.println("sending list of on-line clients");
            try {
                oos.writeUTF(getClients());
                oos.reset();

                System.out.println(getClients());
                System.out.println(clientList.size());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        protected int getClientId() {
            return id;
        }


    }
}