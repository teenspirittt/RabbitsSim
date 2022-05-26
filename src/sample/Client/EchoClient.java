package sample.Client;

import sample.controller.ConfigHandler;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    ConfigHandler configHandler = new ConfigHandler();
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   /* public void sendConfig() throws IOException {
        int count;
        configHandler.saveConfig();
        in.close();
        in = new BufferedInputStream(new FileInputStream("src/resources/data/config.properties"));
        byte[] buffer = new byte[8192];
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
    }*/

    public void sendFile() throws Exception {
        configHandler.saveConfig();
        int bytes;
        File file = new File("src/resources/data/config.properties");
        FileInputStream fis = new FileInputStream(file);
      //  out.writeLong(file.length());
        byte[] buffer = new byte[8192];
        while ((bytes = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytes);
            out.flush();
        }
        fis.close();
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
}