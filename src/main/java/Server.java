import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);) {

            String actualTown = null;

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String acceptedTown = null;

                    if (actualTown == null) {
                        out.println("???");
                        acceptedTown = in.readLine();
                        out.println("OK");
                        actualTown = acceptedTown;
                        System.out.println("Текущее название города: " + actualTown);
                    } else {
                        out.println(actualTown);
                        acceptedTown = in.readLine();
                        if (actualTown.toLowerCase().charAt(actualTown.length() - 1) == acceptedTown.toLowerCase().charAt(0)) {
                            actualTown = acceptedTown;
                            out.println("OK");
                            System.out.println("Текущее название города: " + actualTown);
                        } else {
                            out.println("NOT OK");
                            System.out.println("Название города введено неверно, текущее название города: " + actualTown);
                        }
                    }
                }
            }
        }
    }
}
