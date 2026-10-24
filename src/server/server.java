package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


import common.Calcul;

public class server {
    
    public static void main (String[] args) throws IOException, ClassNotFoundException{

        ServerSocket serversocket = new ServerSocket(1234);
        System.out.println("Socket is onlin. Waiting fort clients on port 1234...");

        Socket socket = serversocket.accept();
        System.out.println("Client is finally connected ! ");

        DataOutputStream STC = new DataOutputStream(socket.getOutputStream());
        DataInputStream RFC = new DataInputStream(socket.getInputStream());
        ObjectInputStream OIS = new ObjectInputStream(socket.getInputStream());
        int cont = 1;

        while(cont == 1){

            Calcul calcul = (Calcul) OIS.readObject();
            double result = 0;
            int num1 = calcul.getNum1();
            char operator = calcul.getOperator();
            int num2 = calcul.getNum2();

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
