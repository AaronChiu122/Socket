//package sockettest;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Connent extends Thread {

    Connent() {
        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            sleep((int) (100 * Math.random()));
             Controlpi.txa1.setText(null);
            Socket s = new Socket("120.107.144.235", 2526);
            Socket sv = new Socket("120.107.144.235", 2525);
            new CClient_send(s).start();
            new CClient_Recv(sv).start();

        } catch (Exception ex) {
           new Connent().start();
        }
    }
}

class CClient_send extends Thread {

    static int flag_send = 0;
    static String str;
    Socket s;

    public CClient_send(Socket socket) {
        this.s = socket;
    }

    public void run() {
        try {

            Controlpi.txa2.addKeyListener(new KeyLis());

            Controlpi.txa1.append("Connected with server for sending successfully!!\n");
            System.out.println("Connected with server for sending successfully!!");

            System.out.println("Data transfering...");
            OutputStream out = s.getOutputStream();

            while (true) {
                if (flag_send == 1) {
         
                    str = Controlpi.txa2.getText();
                    out.write(str.getBytes());
                 //   str = str.replace("\\s+", "");
                    System.out.println("Send:");
                    System.out.print(str);
                    Controlpi.txa1.append("Client: " + Controlpi.txa2.getText());
                    Controlpi.txa2.setText("\r");
                    flag_send = 0;
                    str = null;
                }
                sleep((int) (100 * Math.random()));
            }
 
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    static class KeyLis extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                flag_send = 1;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
            }

        }
    }
}

class CClient_Recv extends Thread {

    String open;
    CallCr ccr = new CallCr();
    Socket s;

    public CClient_Recv(Socket socket) {
        this.s = socket;
    }

    public void run() {
        byte buff[] = new byte[1024];
        try {

            Controlpi.txa1.append("Connected with server for receiving successfully!!\n");

            InputStream in = s.getInputStream();
            int n;
            while (true) {

                n = in.read(buff);
                Controlpi.txa1.append("Server: " + new String(buff, 0, n));
                System.out.println("Received from server: ");
                System.out.println(new String(buff, 0, n));
                open = new String(buff, 0, n);
                ccr.setValue(open);
                sleep((int) (100 * Math.random()));
            }

        } catch (Exception e) {
            System.out.println("Error:" + e);

            new Connent().start();
        }
    }
}
