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
    // 58 38
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
