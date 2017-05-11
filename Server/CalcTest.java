
package aaronserver;
public class CalcTest {
    private String rec="";
    private String Total="";
    private String feedback="";
    String one2four;
    public String getRec() {
        String s = feedback;
        feedback="";
        return s;
    }

    public void setRec(String rec) {
            this.rec = rec;
            Direct();
    }
    private void Direct(){
        if(rec.length()>=4){
           one2four=rec.substring(0, 4); 
            if(one2four.equals("TIME")){
                System.out.println("one2four:" + one2four);
                Calculat();
            }
        }
        
    }
    private void Calculat(){
        
        try{
            String[] tokens = rec.split(",");
//        for (String token:tokens) {
//            System.out.println(token);
//        }
            String water=tokens[1],
                    foam=tokens[2],
                    dry=tokens[3];
            test_database.get_test_database().insertData2("testTime",Integer.parseInt(water),
                    Integer.parseInt(foam),Integer.parseInt(dry));
            
            Total=String.valueOf(Integer.valueOf(water)*10+ //water
                Integer.valueOf(foam)*15+  		//foam
                Integer.valueOf(dry)*20); 		//drying
            
            
            System.out.println("Total:"+Total);
            Total=null;
            rec=null;
            feedback="111142525836965555";
        }catch(Exception e){
            feedback="CAL";
            System.out.println("client端輸入的數據不對");   
            System.out.println("CalErr"+e);
        }
        
    }
    
}
