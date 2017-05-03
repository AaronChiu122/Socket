
public class CallCr {
    String value,Cal;
    long wtime ,etime ,rtime;
    
 
    public void setValue(String str){
        value=str;
       ifValue();
        
        
    }
    public void setWtime(long time){
        wtime=time;
    }
    public void setEtime(long time){
         etime=time;
    }
    public void setRtime(long time){
         rtime=time;
    }
    public String getValue(){
        return value;
    }
    
    public void ifValue(){
            if(getValue().replaceAll("\\s+", "").equals("NO")){
                Controlpi.NO();
                
            }else if(getValue().replaceAll("\\s+", "").equals("OFF")){
                 Controlpi.OFF();
             }else if(getValue().replaceAll("\\s+", "").equals("CAL")){
                 Cal="cal,"+wtime+","+etime+","+rtime;
                 CClient_send.str = Cal;
                 CClient_send.flag_send=1;
                 
             }

        
        
    }


}
