

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
            Total=String.valueOf(Integer.valueOf(tokens[1])*10+ //water
                Integer.valueOf(tokens[2])*15+  				//foam
                Integer.valueOf(tokens[3])*20); 				//drying
            System.out.println("Total:"+Total);
            Total=null;
            rec=null;
            feedback="111142525836965555";
        }catch(Exception e){
            feedback="CAL";
            System.out.println("client�ݿ�J���ƾڤ���");   
            System.out.println("CalErr"+e);
        }
        
    }
    
}
