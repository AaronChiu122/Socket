
public class continueTime {

    long startTime, endTime, totalTime, currTime;

    public void setStartTime(Long time) {
        startTime = time;
    }

    public void setEndTime(Long time) {
        endTime = time;
    }
     public void setCurrTime(Long time) {
        currTime = time;
    }

    public void count() {
        totalTime = totalTime +(endTime -startTime) / 1000;

    }
       public long count_ON() {
       
       return (currTime-startTime)/1000;
    }
}
