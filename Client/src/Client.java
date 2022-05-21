

import sample.controller.ConfigHandler;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.0.12", 8000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("connected to server!");
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
