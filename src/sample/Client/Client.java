package sample.Client;


import sample.controller.ConfigHandler;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    private static OutputStream os = null;
    private static InputStream is = null;
    ConfigHandler configHandler = new ConfigHandler();

    public void run() {
        try (Socket socket = new Socket("192.168.0.12", 8000);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));

        ) {

            os = socket.getOutputStream();
            System.out.println("Connected to server 192.168.0.12:8000!");
            String request = "aboba";
            writer.write(request);
            writer.newLine();
            writer.flush();
            String response = br.readLine();
            System.out.println("Response: " + response);

            while(!socket.isClosed()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendConfig() throws IOException {
        configHandler.saveConfig();
        is = new FileInputStream("src/resources/data/config.properties");
        int count;
        byte[] buffer = new byte[8192];
        while ((count = is.read(buffer)) > 0) {
            os.write(buffer, 0, count);
        }
    }

}
