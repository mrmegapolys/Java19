package ru.sbt.shop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ShopClientServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1919);
        System.out.println("Server started.");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New connection.");

            new Thread(() -> {
               try {
                   echo(clientSocket);
               } catch (IOException | ClassNotFoundException e) {
                   System.out.println("exception");
                   System.out.println(e);
                   try {
                       clientSocket.close();
                   } catch (IOException ignored) {
                   }
               }
            }).start();
        }
    }

    private static void echo(Socket clientSocket) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
        Object o = inputStream.readObject();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new ObjectOutputStream(out).writeObject(o);
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write(out.toByteArray());
        clientSocket.close();
    }

    private static void processSocket(Socket clientSocket) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
        Order order = (Order) inputStream.readObject();

        System.out.println(order.toString());
        Result result = processOrder(order);
        System.out.println(result.toString());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new ObjectOutputStream(out).writeObject(result);
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write(out.toByteArray());
        clientSocket.close();
    }

    private static Result processOrder(Order order) {
        return new Result(order.getPrice() > 500);
    }
}
