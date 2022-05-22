

import sample.controller.ConfigHandler;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    public void run() {
        try (Socket socket = new Socket("192.168.0.12", 8000);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
             DataOutputStream oos =
                     new DataOutputStream(
                             new BufferedOutputStream((socket.getOutputStream())));
             DataInputStream ois =
                     new DataInputStream(
                             new BufferedInputStream(socket.getInputStream()));
        ) {

            System.out.println("Connected to server 192.168.0.12:8000!");
            String request = "aboba";
            writer.write(request);
            writer.newLine();
            writer.flush();
            String response = br.readLine();
            System.out.println("Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendConfig() {
        ConfigHandler configHandler = new ConfigHandler();
        configHandler.saveConfig();

    }

}
