package sample.Server.src;

import sample.controller.Controller;
import sample.controller.PropertyPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class EchoMultiServer {

    private static EchoMultiServer instance;
    private ServerSocket serverSocket;
    private int clientCount = 0;
    private Vector<EchoClientHandler> clientList = new Vector<>();
    private int id;

    public PropertyPackage prpPck;
    public PropertyPackage tmp;


    public static synchronized EchoMultiServer getInstance() {
        if (instance == null) {
            instance = new EchoMultiServer();
        }
        return instance;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                EchoClientHandler ech = new EchoClientHandler(serverSocket.accept(), id++);
                clientList.add(ech);
                ech.start();
                clientCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public String getClients() {
        StringBuilder names = new StringBuilder();
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
                oos.writeUTF(ems.getClients());
                System.out.println(ems.getClients());
                oos.reset();

            } catch (IOException e) {
                e.printStackTrace();
            }
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