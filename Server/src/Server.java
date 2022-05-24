import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static OutputStream os = null;
    private static InputStream is = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()));
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));

                new Thread(() -> {
                    try {
                        String request = br.readLine();
                        writer.write("Hello from server, " + request);
                        System.out.println("Request: " + request);
                        writer.newLine();
                        writer.flush();

                        is = socket.getInputStream();
                        os = new FileOutputStream("Server/resources/sendedConfig.properties");

                        byte[] buffer = new byte[8192];
                        int count;
                        while ((count = is.read(buffer)) > 0) {
                            os.write(buffer, 0, count);
                        }

                        socket.close();
                        writer.close();
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
