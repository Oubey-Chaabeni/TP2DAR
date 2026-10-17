package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client{

    public static void main (String[] args) throws IOException{

        Scanner stScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);


        System.out.println("Client is Trying to connect to the server...");
        
        Socket socket = new Socket("127.0.0.1",1234);
        System.out.println("Client is connected to the server !");

        DataOutputStream STS = new DataOutputStream(socket.getOutputStream());
        DataInputStream RFS = new DataInputStream(socket.getInputStream());
        PrintWriter SBTS = new PrintWriter(socket.getOutputStream(), true);
        int cont = 1;

        while(cont == 1){
            System.out.println("================== CALCULATOR SERVICE =====================");

            boolean validateFormat = false;
            String operation;

            do{
                System.out.print("Enter the operation you would like done (valid format) : ");
                operation = stScanner.nextLine();

                validateFormat = operation.matches("\\d+[+\\-*/]\\d+");

                if(validateFormat == false)
                {
                    System.out.println("\nInvalid opeartion Format !!!\n");
                }

            }while (validateFormat == false);
            SBTS.println(operation);

            double result = RFS.readDouble();
            System.out.println("The result of the operation sent is "+result);
            System.out.println("\n===========================================================");


            System.out.print("\nDo you like to continue (1:yes/0:no) : ");
            cont = intScanner.nextInt();
            STS.writeInt(cont);
        }
        socket.close();        
        stScanner.close();
        intScanner.close();
        
    }
    
}
