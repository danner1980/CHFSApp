package dan.hunt.com.chfsapp;

/**
 * Created by dan on 31/08/17.
 */

public class SalesInfo {

    String weekNum;
    String salesValue;

    public SalesInfo() {
        //initialise blank salesinfo class
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public String getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(String salesValue) {
        this.salesValue = salesValue;
    }

}
