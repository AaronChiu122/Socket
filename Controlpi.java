/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Controlpi {
    
   
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
   static CallCr ccr=new CallCr();
   String open ;
   final GpioController gpio = GpioFactory.getInstance();
   public static void main (String[] args) throws InterruptedException
   {
     try
      {
         btn1.addActionListener(new Controlpi.ActLis());
         btn2.addActionListener(new Controlpi.ActLis());
         frm.addWindowListener(new Controlpi.WinLis());
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
  
        public static void NO(){
            final GpioController gpio = GpioFactory.getInstance();
            System.out.println("<--Pi4J--> GPIO 02 Control ... started.");
            final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"02", PinState.HIGH);
            pin2.setShutdownOptions(true, PinState.LOW);
            System.out.println("--> GPIO 02 state should be: ON");
   }

    static class ActLis implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Button btn=(Button) e.getSource();
         if(btn==btn1)
         {
            Controlpi.txa1.setText("Waiting for connecting with server("+txf1.getText()+")...\n");
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