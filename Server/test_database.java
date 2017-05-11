/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaronserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class test_database {
//   public static void main(String[] args) throws Exception 
//   { 
//  
//     //建立DB連線
//     jdbcmysql db = new jdbcmysql();
//     //寫入溫度計資料
//     db.insertData("Aaron","55555");
//     //讀取溫度計資料
//     db.SelectData();
//
//    }
//}
//資料庫
//建立jdbc類別
class test_database { 
 
    
   //連接object 
   private Connection con = null; //Database objects 
   //執行,傳入之sql為完整字串 
   private Statement stat = null; 
   //結果集 
   private ResultSet rs = null; 
   private PreparedStatement pst = null; 
   //select:查詢溫度所有資料
   private String selectData2DBSQL = "select * from user"; 
   
   //insert:執行sql，先利用?來做標示傳入變數之位置 
   private String insertData2SQL ; 
   //單例 Singleton
   private static test_database current_test_database= new test_database();
   private test_database(){}
   public static test_database get_test_database(){
       return current_test_database;
   }
   //連線
   public void ConnetDB(){
       try { 
        //註冊driver 
        Class.forName("com.mysql.jdbc.Driver"); 
        
        //取得connection
        //localhost是主機名,fcu_db是database名
        //useUnicode=true&characterEncoding=Big5使用的編碼 
        con = DriverManager.getConnection( 
        "jdbc:mysql://localhost:3306/classicmodels","dbadmin","1234"); 
        } catch(ClassNotFoundException e) { //sqlexception 
            System.out.println("DriverClassNotFound :"+e.toString()); 
            }catch(SQLException x) { 
                System.out.println("Exception :"+x.toString()); 
        } 
   }
   
  
   //查詢資料
   public void SelectData(){ 
     try 
     { 
       stat = con.createStatement(); 
       rs = stat.executeQuery(selectData2DBSQL); 
       System.out.println("username\ttime"); 
       while(rs.next()) 
       { 
         System.out.println(rs.getString("username")+"\t\t"+rs.getString("time")); 
       } 
     } 
     catch(SQLException e) 
     { 
       System.out.println("DropDB Exception :" + e.toString()); 
     } 
     finally 
     { 
       Close(); 
     } 
   } 

   //新增資料 
   public void insertData(String username,String time) 
   { 
     try 
     { 
       insertData2SQL = "insert into user(username,time) " + 
            "VALUES (?,?)";
       pst = con.prepareStatement(insertData2SQL); 
       
       pst.setString(1,username); 
       pst.setString(2,time); 
       pst.executeUpdate(); 
     } 
     catch(SQLException e) 
     { 
       System.out.println("InsertDB Exception :" + e.toString()); 
     } 
     finally 
     { 
       Close(); 
     } 
   } 
   public void insertData2(String username,int water,int foam,int dry) 
   { 
     try 
     { 
       insertData2SQL = "insert into user(username,time,water,foam,dry) " + 
            "VALUES (?,?,?,?,?)";
       pst = con.prepareStatement(insertData2SQL); 
       
       pst.setString(1, username); 
       pst.setString(2, getDateTime());
       pst.setInt(3, water); 
       pst.setInt(4, foam);
       pst.setInt(5, dry);
       
       pst.executeUpdate(); 
     } 
     catch(SQLException e) 
     { 
       System.out.println("InsertDB2 Exception :" + e.toString()); 
     } 
     finally 
     { 
       Close(); 
     } 
   } 

   //完整使用完資料庫後,記得要關閉所有Object 
   //否則在等待Timeout時,可能會有Connection poor的狀況 
   private void Close() 
   { 
     try 
     { 
       if(rs!=null) 
       { 
         rs.close(); 
         rs = null; 
       } 
       if(stat!=null) 
       { 
         stat.close(); 
         stat = null; 
       } 
       if(pst!=null) 
       { 
         pst.close(); 
         pst = null; 
       } 
     } 
     catch(SQLException e) 
     { 
       System.out.println("Close Exception :" + e.toString()); 
     } 
   }
   public String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String strDate = sdFormat.format(date);
        //System.out.println(strDate);
        return strDate;
    }
}