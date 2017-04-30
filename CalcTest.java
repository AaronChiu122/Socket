
package sockettest;

import sockettest.*;

public class CalcTest {
    private String rec;
    private String Total;
    
    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
            this.rec = rec;
            Direct();
    }
    private void Direct(){
        String oneToThree=rec.substring(0, 3);
        System.out.println("前三個字為:" + oneToThree);
        if(oneToThree.equals("cal")){
                Calculat();
        }
    }
    private void Calculat(){
        
        try{
            String[] tokens = rec.split(",");
//        for (String token:tokens) {
//            System.out.println(token);
//        }
            Total=String.valueOf(Integer.valueOf(tokens[1])*10+ //水槍
                Integer.valueOf(tokens[2])*15+ //泡沫
                Integer.valueOf(tokens[3])*20); //烘乾
            System.out.println("Total:"+Total);
            Total=null;
            rec=null;
        }catch(Exception e){
         System.out.println("計算出錯"+e);
        }
        
    }
    
}
