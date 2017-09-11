package dan.hunt.com.chfsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dan on 31/08/17.
 */

public class SalesJSONFormat {

    SalesInfo[] salesInfo;

    public void processSales(String jsonResult) {
        //Temporary Variables
        String quantity = "";
        String weekNum = "";
        try {
            JSONArray jsonArray = new JSONArray(jsonResult);

            //initialise array of SalesInfo objects using the length of the returned json
            salesInfo = new SalesInfo[jsonArray.length()];

            //List to store the weeks and sales
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    //Get the JSONObject from the JSON array
                    JSONObject dataObject = jsonArray.getJSONObject(i);

                    //Create a temporary salesinfo object to store the values
                    SalesInfo temp = new SalesInfo();

                    //Get the sales quantity and week number and store it in a string
                    temp.setSalesValue(dataObject.getString("Quantity"));
                    temp.setWeekNum(dataObject.getString("SalesDate"));

                    Log.d("JSON DATA Sales", temp.getSalesValue());
                    Log.d("JSON DATA Week", temp.getWeekNum());

                    //Add the temporary SalesInfo object to the array
                    salesInfo[i] = temp;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public SalesInfo[] getSalesInfo() {
        return salesInfo;
    }
}
