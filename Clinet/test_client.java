//package sockettest;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


class CClient_send extends Thread
{
   static int flag_send=0;
   static String str;
   public void run()
   {
      try
      {
         Controlpi.txa2.addKeyListener(new KeyLis());
                 
         Socket s=new Socket("120.107.144.235",2526);
         Controlpi.txa1.append("Connected with server for sending successfully!!\n");
         System.out.println("Connected with server for sending successfully!!");
        
         System.out.println("Data transfering...");
         OutputStream out=s.getOutputStream();
        
         while(true)
         {
            if(flag_send==1)
            {
               str=Controlpi.txa2.getText();
               str=str.replace("\\s+", "");
               out.write(str.getBytes());
               flag_send=0;
               System.out.print("Send:"+str);
               str=null;
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
           Controlpi.txa1.append("Client: "+Controlpi.txa2.getText());
           Controlpi.txa2.setText("\r");
         } 
          
      }  
   }
}
class CClient_Recv   extends Thread 
{ String open;
  CallCr ccr=new CallCr();
   public void run()
   {
      byte buff[] = new byte[1024];
      try
      {
         Socket s=new Socket("120.107.144.235",2525);
         Controlpi.txa1.append("Connected with server for receiving successfully!!\n");
 

         InputStream in=s.getInputStream();
         int n;
         while(true)
         {
            
            n=in.read(buff);
            Controlpi.txa1.append("Server: "+new String(buff,0,n));
            System.out.println("Received from server: ");
            System.out.println(new String(buff,0,n));
            open= new String(buff,0,n);
            ccr.setValue(open);
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




  
  
