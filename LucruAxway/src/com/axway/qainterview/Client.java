package com.axway.qainterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int port = 5000;

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        Socket s = null;
        try {
            s = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(in);

        while (true) {
            //citire request
            System.out.print("Request:");
            String request = scan.nextLine();
            String response = "";
            //trimitere request
            pr.print(request+"\n");
            pr.flush();

            //primire raspuns
            try {
                response = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Response:\n" + response);

            if (request.toLowerCase().equals("quit")) {
                break;
            }
        }
    }
}
