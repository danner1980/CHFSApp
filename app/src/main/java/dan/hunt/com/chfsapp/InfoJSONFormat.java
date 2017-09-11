package dan.hunt.com.chfsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dan on 31/08/17.
 */

public class InfoJSONFormat {

    ProductInfo[] productInfo;

    public void processInfo(String jsonResult) {
        //Temporary Variables
        String description = "";
        String barcode = "";
        String price = "";
        String dateLastSold = "";

        try {
            JSONArray jsonArray = new JSONArray(jsonResult);

            //initialise array of SalesInfo objects using the length of the returned json
            productInfo = new ProductInfo[jsonArray.length()];

            //List to store the weeks and sales
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    //Get the JSONObject from the JSON array
                    JSONObject dataObject = jsonArray.getJSONObject(i);

                    //Create a temporary salesinfo object to store the values
                    ProductInfo temp = new ProductInfo();

                    //Get the sales quantity and week number and store it in a string
                    temp.setDescription(dataObject.getString("Description"));
                    temp.setBarcode(dataObject.getString("Barcode"));
                    temp.setPrice(dataObject.getString("Price"));
                    temp.setDatelastsold(dataObject.getString("DateLastSold"));



                    //Add the temporary SalesInfo object to the array
                    productInfo[i] = temp;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ProductInfo getProductInfo() {
        return productInfo[0];
    }
}
