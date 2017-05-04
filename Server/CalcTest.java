

public class CalcTest {
    private String rec="";
    private String Total="";
    private String feedback="";
    String one2Three;
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
        if(rec.length()>=3){
           one2Three=rec.substring(0, 3); 
            if(one2Three.equals("cal")){
                System.out.println("one2Three:" + one2Three);
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
            feedback="OFF";
        }catch(Exception e){
            feedback="CAL";
            System.out.println("client端輸入的數據不對");   
            System.out.println("CalErr"+e);
        }
        
    }
    
}
