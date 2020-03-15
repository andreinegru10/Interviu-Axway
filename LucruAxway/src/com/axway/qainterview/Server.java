package com.axway.qainterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static String solveRequest(String request) {
        String response;
        String[] tokens = request.split("\\s");

        if (tokens.length < 1) {
            response = "Invalid command";
        }
        else if (tokens.length == 1 && tokens[0].toLowerCase().equals("quit")) {
            response = "The server is going out!";
        }
        else if (tokens.length == 1 && tokens[0].toLowerCase().equals("help")) {
            response = Rest.help();
        }
        else if (tokens.length == 1 && tokens[0].toLowerCase().equals("get")) {
            response = Rest.get();
        }
        else if (tokens.length == 1 && tokens[0].toLowerCase().equals("del")) {
            response = Rest.delete();
        }
        else if (tokens.length == 2 && tokens[0].toLowerCase().equals("get")) {
            response = Rest.get(tokens[1]);
        }
        else if (tokens.length == 2 && tokens[0].toLowerCase().equals("add")) {
            response = Rest.addNode(tokens[1]);
        }
        else if (tokens.length == 2 && tokens[0].toLowerCase().equals("del")) {
            response = Rest.delNode(tokens[1]);
        }
        else if (tokens.length == 3 && tokens[0].toLowerCase().equals("add")) {
            response = Rest.addEdge(tokens[1], tokens[2]);
        }
        else if (tokens.length == 3 && tokens[0].toLowerCase().equals("del")) {
            response = Rest.delEdge(tokens[1], tokens[2]);
        }
        else {
            response = "Invalid command";
        }

        return response;
    }

    public static void main(String[] args) {

        int port = 5000;

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket s = null;
        try {
            s = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client connected");
        Rest.initGraph();

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
            String request = null;
            try {
                request = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String response;
            System.out.println("Request: " + request);

            response = solveRequest(request);

            pr.println(response);
            pr.flush();

            if (request.toLowerCase().equals("quit")) {
                break;
            }
        }
    }
}
