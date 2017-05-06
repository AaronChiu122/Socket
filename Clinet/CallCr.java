
public class CallCr {

    String value, Cal;
    static  int sWaterTime, sDryTime, sFomatime;
    //  static long wtime=Controlpi.wtime ,etime=Controlpi.etime ,rtime=Controlpi.rtime;

    public void setValue(String str) {
        value = str;
        ifValue();

    }

    public String getValue() {
        return value;
    }

    public void ifValue() {

        if (getValue().replaceAll("\\s+", "").length() > 4) {
            if (getValue().replaceAll("\\s+", "").substring(0, 4).equals("TIME")) {
                setTime();
            }
        }

        if (getValue().replaceAll("\\s+", "").equals("ON")) {
            Controlpi.NO();

        } else if (getValue().replaceAll("\\s+", "").equals("OFF")) {
            Controlpi.OFF();
        } else if (getValue().replaceAll("\\s+", "").equals("CAL")) {
            Cal = "cal,";
            Controlpi.txa2.setText(Cal);
            CClient_send.flag_send = 1;

        }

    }

    public void setTime() {
        String[] str = getValue().replaceAll("\\s+", "").split(",");
        sWaterTime = Integer.valueOf(str[1]);
        sDryTime =  Integer.valueOf(str[2]);
        sFomatime =  Integer.valueOf(str[3]);
       
        Controlpi.useTime[0] += sWaterTime;
        Controlpi.useTime[1] +=  sDryTime;
        Controlpi.useTime[2] +=  sFomatime;
         System.out.print(Controlpi.useTime[0] + "," + Controlpi.useTime[1] + "," + Controlpi.useTime[2]);
    }

}
