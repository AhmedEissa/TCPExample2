package tcpexample2;

import java.io.*;
import java.net.*;

class TCPClient {

    public static void main(String argv[]) throws IOException {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);
        while (true) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Type your text: \n or type end to terminate connection.");
            sentence = inFromUser.readLine();
            if (sentence.equals("end")) {
                outToServer.writeBytes("connectionTerminated");
                break;
            }
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
        clientSocket.close();
    }
}
