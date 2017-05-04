
public class CallCr {
    String value,Cal;
  //  static long wtime=Controlpi.wtime ,etime=Controlpi.etime ,rtime=Controlpi.rtime;
    
 
    public void setValue(String str){
        value=str;
       ifValue();
        
        
    }
//    public void setWtime(long time){
//        wtime=time;
//    }
//    public void setEtime(long time){
//         etime=time;
//    }
//    public void setRtime(long time){
//         rtime=time;
//    }
    public String getValue(){
        return value;
    }
    
    public void ifValue(){
           
                
            if(getValue().replaceAll("\\s+", "").substring(0,4).equals("TIME")) {
                    setTime();
                }
            
                
            if(getValue().replaceAll("\\s+", "").equals("ON")){
                Controlpi.NO();
                
            }else if(getValue().replaceAll("\\s+", "").equals("OFF")){
                 Controlpi.OFF();
             }else if(getValue().replaceAll("\\s+", "").equals("CAL")){
                 Cal="cal,"+Controlpi.watertime+","+Controlpi.drytime+","+Controlpi.foamtime;
               	 Controlpi.txa2.setText(Cal);
                 CClient_send.flag_send=1;
                 
             }

        
        
    }
    public void setTime(){
        String str;
        str = getValue().replaceAll("\\s+", "");
        
    }


}
