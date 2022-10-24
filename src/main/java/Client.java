import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {

            String answer = in.readLine();

            if (answer.equals("???")) {
                System.out.println("Введите любое название города");
            } else {
                System.out.println("Текущее название города: " + answer);
                System.out.println("Введите название города начинающееся на букву: " + answer.toLowerCase().charAt(answer.length() - 1));
            }

            Scanner scan = new Scanner(System.in);
            String request = scan.nextLine();

            out.println(request);

            answer = in.readLine();

            if (answer.equals("OK")) {
                System.out.println("Ваше название принято");
            }
            if (answer.equals("NOT OK")) {
                System.out.println("Вы ввели неверное название");
            }
        }
    }
}
