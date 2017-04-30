package sockettest;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

class CClient_send extends Thread
{
   static int flag_send=0;
   public void run()
   {
      try
      {
         test_client.txa2.addKeyListener(new KeyLis());
                 
         Socket s=new Socket(test_client.txf1.getText(),2526);
         test_client.txa1.append("Connected with server for sending successfully!!\n");
         System.out.println("Connected with server for sending successfully!!");
        
         System.out.println("Data transfering...");
         OutputStream out=s.getOutputStream();
         String str;
         while(true)
         {
            if(flag_send==1)
            {
               str=test_client.txa2.getText();
               out.write(str.getBytes());
               flag_send=0;
               System.out.print("Send:"+str);
            }
            sleep((int)(100*Math.random())); 
         }
         //in.close();
         //out.close();
         //s.close();
      }
      catch(Exception e)
      {
         System.out.println("Error:"+e);
      }
   }
   static class KeyLis extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_ENTER)
         { 
            flag_send=1;
         }
      }
      public void keyReleased(KeyEvent e)
      {  
         if(e.getKeyCode()==KeyEvent.VK_ENTER)
         { 
            test_client.txa1.append("Client: "+test_client.txa2.getText());
            test_client.txa2.setText("\r");
         } 
          
      }  
   }
}
class CClient_Recv extends Thread
{
   public void run()
   {
      byte buff[] = new byte[1024];
      try
      {
         Socket s=new Socket(test_client.txf1.getText(),2525);
         test_client.txa1.append("Connected with server for receiving successfully!!\n");
         System.out.println("Connected with server for receiving successfully!!");
        
         InputStream in=s.getInputStream();
         int n;
         while(true)
         {
            n=in.read(buff);
            test_client.txa1.append("Server: "+new String(buff,0,n));
            System.out.print("Received from server: ");
            System.out.print(new String(buff,0,n));
            sleep((int)(100*Math.random())); 
         }
         //in.close();
         //s.close();
      }
      catch(Exception e)
      {
         System.out.println("Error:"+e);
      }
   }
}


public class test_client
{
   static Frame frm=new Frame("JAVA Socket Client AWT Program");
   static Button btn1=new Button("Connect");
   static Button btn2=new Button("Exit");
   static Label lab1=new Label("Server IP Address");
   static Label title=new Label("JSocket");
   static TextArea txa1=new TextArea("",6,10,TextArea.SCROLLBARS_VERTICAL_ONLY);
   static TextArea txa2=new TextArea("",6,10,TextArea.SCROLLBARS_NONE);
   static TextField txf1=new TextField("127.0.0.1");
   static CClient_Recv cr=new CClient_Recv();
   static CClient_send cs=new CClient_send();
  
   public static void main (String[] args)
   {
     try
      {
         btn1.addActionListener(new ActLis());
         btn2.addActionListener(new ActLis());
         frm.addWindowListener(new WinLis());
         frm.setLayout(null);
         title.setBounds(20,40,75,40);
         btn1.setBounds(280,40,100,20);
         btn2.setBounds(280,65,100,20);
         frm.setBounds(450,150,400,300);
         frm.setBackground(new Color(151,255,255));
         lab1.setBounds(100,40,160,20);
         txa1.setBounds(20,95,360,140);
         txa2.setBounds(20,240,360,40);
         txf1.setBounds(100,65,160,20);
         txa1.setEditable(false);
         title.setFont(new Font("Serief",Font.BOLD+Font.ITALIC,18));
         title.setForeground(Color.BLUE);
         frm.add(title);
         frm.add(btn1);frm.add(btn2);
         frm.add(lab1);
         frm.add(txa1);frm.add(txa2);
         frm.add(txf1);
         frm.setVisible(true);
        
      }
      catch(Exception e)
      {
         System.out.println("Error:"+e);
      }
   }
  
   static class ActLis implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Button btn=(Button) e.getSource();
         if(btn==btn1)
         {
            test_client.txa1.setText("Waiting for connecting with server("+txf1.getText()+")...\n");
            System.out.println("Waiting for connecting with server...");
            txf1.setEditable(false);
            cs.start();
            cr.start();
         }
         else if(btn==btn2)
            System.exit(0);
         else
            System.out.println("No Button Click!");
      }
   }
   static class WinLis extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {
         frm.dispose();
         System.exit(0);
      }
   }
}