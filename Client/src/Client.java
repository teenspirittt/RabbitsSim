
import sample.controller.ConfigHandler;

import java.io.*;
import java.net.Socket;

public class Client {

    private static OutputStream os = null;
    private static InputStream is = null;

    public static void main(String[] args) {
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


            ConfigHandler configHandler = new ConfigHandler();
            configHandler.saveConfig();
            is = new FileInputStream("src/resources/data/config.properties");

            int count;
            byte[] buffer = new byte[8192];
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void sendConfig() throws IOException {


    }


}
