package com.centris.campus.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListener {
	public static void main(String[] args) {
	    new ServerListener().startServer();
	}
	public void startServer() {
	    final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(03);

	    Runnable serverTask = new Runnable() {
	        @SuppressWarnings("resource")
	        @Override
	        public void run() {
	            try {
	                ServerSocket serverSocket = new ServerSocket(7027);
	                System.out.println("Waiting for clients to connect...");
	                while (true) {
	                    Socket clientSocket = serverSocket.accept();
	                    clientProcessingPool.submit(new ClientTask(clientSocket));
	                }
	            } catch (IOException e) {
	                System.err.println("Unable to process client request");
	                e.printStackTrace();
	            }
	        }
	    };
	    Thread serverThread = new Thread(serverTask);
	    serverThread.start();
	}

	private class ClientTask implements Runnable {
	    private final Socket clientSocket;
	  
	    private ClientTask(Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    @Override
	    public void run() {
	        System.out.println("Got a client !");
	        while (true) {
	        	 DataInputStream input;
	        	 PrintStream output;
	        try {
	            BufferedReader reader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
	            String clientData = "";

	            clientData = reader.readLine();
	         if(clientData.equalsIgnoreCase("null")) {
	        	 
	         }else {
	        	  String clientAddress = clientSocket.getInetAddress().getHostAddress();
	        	  int clientPort = clientSocket.getPort();
	        	 System.out.println("clientdata::::"+clientData);
	        
	        	 System.out.println("clientAddress is "+clientAddress+":"+clientPort);
	        	 /*String[] realData=clientData.split("?");
	        	 
	        	 
	        	 System.out.println("realData is "+realData[0]);
	        	 
	        	 String[] individualData=realData[0].split("&");
	        	 String machineId=individualData[0].split("=")[1];
	        	 */
	        	/* int red = -1;
	        	 byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
	        	 byte[] redData;
	        	 StringBuilder clientData1 = new StringBuilder();
	        	 String redDataText="";
	        	 while ((red = clientSocket.getInputStream().read(buffer)) > -1) {
	        	     redData = new byte[red];
	        	     System.arraycopy(buffer, 0, redData, 0, red);
	        	     redDataText = new String(redData,"UTF-8"); // assumption that client sends data UTF-8 encoded
	        	     System.out.println("message part recieved:" + redDataText); 
	        	     clientData1.append(redDataText);
	        	 }
	        	 */
	        
	         
	        	 String returnMessage;
	              
	                   
	                 
	                    returnMessage = "GET /iclock/getrequest?SN=6740164500457";
	                
	                    System.out.println("Message is "+returnMessage);
	                //Sending the response back to the client.
	                OutputStream os = clientSocket.getOutputStream();
	                OutputStreamWriter osw = new OutputStreamWriter(os);
	                BufferedWriter bw = new BufferedWriter(osw);
	                bw.write(returnMessage);
	                System.out.println("Message sent to the client is "+returnMessage);
	                bw.flush();
	         
	         }
	            	  
	          


	       
	        	
	        
	        	 input = new DataInputStream(clientSocket.getInputStream());
	        	 output = new PrintStream(clientSocket.getOutputStream());
	        
	        
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    }


	 }
}
