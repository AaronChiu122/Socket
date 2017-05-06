

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

class Connect extends Thread{
    static AtomicInteger onlineCount = new AtomicInteger(0);
    static Vector output;
    public Connect(){
        output = new Vector();
    }
        public void run()
        {
            try{
                ServerSocket svsSend = new ServerSocket(2525);
                ServerSocket svsRecv = new ServerSocket(2526);
                for (;;) {  
                    Socket incomingSend = svsSend.accept( );
                    Socket incomingRecv = svsRecv.accept( );
                    
                    OutputStream out=incomingSend.getOutputStream();
                    output.add(out);
                    
                    System.out.println("ID : " + onlineCount+ ", Connection Started");
                    System.out.println("目前線上人數：" + onlineCount);

                    new CServer_send().start();
                    new CServer_Recv(incomingRecv,onlineCount.intValue()).start();
                    onlineCount.getAndIncrement();
                    sleep((int)(100*Math.random()));
                }

            }catch(Exception e)
            {
               System.out.println("Error IN Connect:"+e);
            }
        }    
}

class CServer_send extends Thread{
    public static String str="";
   static int flag_send=0;
   public void run(){
      try{
         test_server.txa2.addKeyListener(new KeyLis());
        
         test_server.txa1.append("Clinet connecting for sending successfully!!\n");
         System.out.println("Clinet connecting for sending successfully!!");
        
         System.out.println("Data transfering...");
         
         
         while(true){
            if(flag_send==1){
                
                str=test_server.txa2.getText();
                test_server.txa1.append("Server: "+test_server.txa2.getText());
                String[] tokens = str.split(",");
                if(tokens.length>=2){
                    int ID=Integer.valueOf(str.substring(0, 1) );
                     
                    OutputStream writer = (OutputStream)Connect.output.elementAt(ID);
                    writer.write(tokens[1].getBytes());
                    writer.flush();

                    test_server.txa1.append("Server to "+ID+":"+tokens[1]+"\n");
                    System.out.print("Send "+ID+":"+tokens[1]+"\n");
                    tokens=null;
                }
                flag_send=0;
            }
            
            str="";
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
            test_server.txa2.setText("\r");
         } 
          
      }  
   }
}


class CServer_Recv extends Thread{
    
    private Socket s;
    private int onlineCount;
    String RecvString="";
    String feedback="";
    CalcTest calc;
    static int flag_send=0;
    
    public CServer_Recv(Socket s,int onlineCount){
        this.s = s;
        this.onlineCount=onlineCount;
        calc=new CalcTest();
    }
    
    public void run()
    {
      byte buff[] = new byte[1024];
      try
      {
         test_server.txa1.append("Clinet connecting for receiving successfully!!\n");
         System.out.println("Clinet connecting for receiving successfully!!");
        
         InputStream in=s.getInputStream();
         int n;
         while(true)
         {
            n=in.read(buff);
            RecvString=new String(buff,0,n);
            RecvString = RecvString.replaceAll("\\s+", "");//!!!!!
            test_server.txa1.append("Client "+onlineCount+":"+RecvString+"\n");
            System.out.print("Received from client "+onlineCount+":"+RecvString+"\n");
            
            if(RecvString!=""){
                
                calc.setRec(RecvString);
                feedback=calc.getRec();
                OutputStream writer = (OutputStream)Connect.output.elementAt(onlineCount);
                writer.write(feedback.getBytes());
                writer.flush();

                feedback = "";
                RecvString = ""; 
                
            }
            

            sleep((int)(100*Math.random())); 
         }
         
         //in.close();
         //s.close();
      }
      catch(Exception e)
      {
        System.out.println("RecvErr:"+e);
      }
   }
}

public class test_server
{
   static Frame frm=new Frame("JAVA Socket Server AWT Program");
   static Button btn1=new Button("Start");
   static Button btn2=new Button("Exit");
   static Label lab1=new Label("Host IP Address");
   static Label title=new Label("JSocket");
   static TextArea txa1=new TextArea("",6,10,TextArea.SCROLLBARS_VERTICAL_ONLY);
   static TextArea txa2=new TextArea("",6,10,TextArea.SCROLLBARS_NONE);
   static TextField txf1=new TextField("127.0.0.1");
//   static CServer_send ss=new CServer_send();
//   static CServer_Recv sr=new CServer_Recv();

   public test_server() 
   {
     try
      {
//         InetAddress adr=InetAddress.getLocalHost();
         txf1.setText(getIp());
         btn1.addActionListener(new ActLis());
         btn2.addActionListener(new ActLis());
         frm.addWindowListener(new WinLis());
        
         frm.setLayout(null);
         title.setBounds(20,40,75,40);
         btn1.setBounds(280,40,100,20);
         btn2.setBounds(280,65,100,20);
         frm.setBounds(100,100,400,300);
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
   public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
   static class ActLis implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Button btn=(Button) e.getSource();
         if(btn==btn1)
         {
            txa1.setText("Waiting for connecting("+txf1.getText()+")...\n");
            System.out.println("Waiting for connecting...");
            txf1.setEditable(false);
            new Connect().start();
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
