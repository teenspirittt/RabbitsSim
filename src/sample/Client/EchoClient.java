package sample.Client;

import sample.controller.ConfigHandler;
import sample.controller.PropertyPackage;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    ConfigHandler configHandler = new ConfigHandler();
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    PropertyPackage propertyPackage = new PropertyPackage();

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getObj() {
        try {
            oos.writeUTF("GET.PACKAGE");
            oos.reset();
            propertyPackage = (PropertyPackage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendObj() {
        try {
            oos.writeUTF("SEND.PACKAGE");
            oos.reset();
            oos.writeObject(propertyPackage);
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile() throws Exception {
        configHandler.saveConfig();
        int bytes;
        out.flush();
        File file = new File("src/resources/data/config.properties");
        FileInputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[8192];
        while ((bytes = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytes);
            out.flush();
        }
        out.flush();
        fis.close();
    }
}