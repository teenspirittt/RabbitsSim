package sample.Client;


import sample.controller.PropertyPackage;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    private Socket clientSocket;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    PropertyPackage propertyPackage = new PropertyPackage();

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);

            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PropertyPackage getObj() {
        try {
            oos.writeUTF("GET.PACKAGE");
            oos.reset();
            propertyPackage = (PropertyPackage) ois.readObject();
            propertyPackage.printProperties();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return propertyPackage;
    }

    public void sendObj(PropertyPackage propertyPackage) {
        try {
            oos.writeUTF("SEND.PACKAGE");
            oos.reset();

            oos.writeObject(propertyPackage);
            propertyPackage.printProperties();

            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientsList(){
        String tmp = null;
        try {
            oos.writeUTF("GET.CLIENTS");
            oos.reset();
            tmp = ois.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public void stopConnection() {
        try {
            ois.close();
            oos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}