
public class continueTime {
    long startTime,endTime,totalTime;
    
    public void setStartTime(Long time){
        startTime = time;
    }     
    public void setEndTime(Long time){
        endTime = time;
    }
    public void count(){
       totalTime=totalTime+ (endTime-startTime)/1000;
       
    }
    
}
