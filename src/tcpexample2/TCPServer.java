package tcpexample2;

import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String argv[]) throws IOException {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            while (connectionSocket.isConnected()) {
                BufferedReader inFromClient
                        = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                clientSentence = inFromClient.readLine();
                System.out.println("Received: " + clientSentence);
                writeToFile(clientSentence);
                capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
        }
    }

    public static void writeToFile(String textFromClient) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("resources/myfile.txt", true));
        writer.write(textFromClient + "\n");
        writer.close();
    }

}
