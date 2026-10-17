package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class server {
    
    public static void main (String[] args) throws IOException{

        ServerSocket serversocket = new ServerSocket(1234);
        System.out.println("Socket is onlin. Waiting fort clients on port 1234...");

        Socket socket = serversocket.accept();
        System.out.println("Client is finally connected ! ");

        DataOutputStream STC = new DataOutputStream(socket.getOutputStream());
        DataInputStream RFC = new DataInputStream(socket.getInputStream());
        BufferedReader RBFC = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int cont = 1;

        while(cont == 1){

            String operation = RBFC.readLine();
            double result = 0;
            int num1=0;
            char operator='+';
            int num2= 0;

            Pattern pattern = Pattern.compile("(\\d+)([+\\-*/])(\\d+)");
            Matcher matcher = pattern.matcher(operation);

            if(matcher.matches()){
            num1 = Integer.parseInt(matcher.group(1));
            operator = matcher.group(2).charAt(0);
            num2 = Integer.parseInt(matcher.group(3));
            }

            System.out.println("Client sent "+num1+" "+operator+" "+num2+" to be calculated. Calculating result....");

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            
                default:
                    break;
            }

            STC.writeDouble(result);
            System.out.println(result+" has been sent to the client !");
            cont = RFC.readInt();

        }

        serversocket.close();
        socket.close();
    }
}
