
public class CallCr {
    String value;
    
 
    public void setValue(String str){
        value=str;
       ifValue();
        
        
    }
    public String getValue(){
        return value;
    }
    
    public void ifValue(){
            if(getValue().subSequence(0, 1).equals("2")){
                Controlpi.NO();
                
            }else if(getValue().subSequence(0, 1).equals("3")){
                
             }

        
        
    }


}
