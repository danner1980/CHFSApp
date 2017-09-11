package dan.hunt.com.chfsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CHFSActivity extends AppCompatActivity {

    //Initialise visual components
    TextView descView, barcodeView, priceView, dateLastView;
    Button getDataButton;
    String infoResult;

    //JSON Formatters for info and sales
    InfoJSONFormat infoFormatter;
    SalesJSONFormat salesFormatter;


    //Initialise OKHttp
    OkHttpClient client;

    //Initialise Datefinder


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chfs);

        infoResult = "";

        //Initialise OKHttp
        client = new OkHttpClient();

        //Initialise sales and info formatters
        infoFormatter = new InfoJSONFormat();
        salesFormatter = new SalesJSONFormat();

        //Setup textviews
        descView = (TextView) findViewById(R.id.descView);
        barcodeView = (TextView) findViewById(R.id.barcodeView);
        priceView = (TextView) findViewById(R.id.priceView);
        dateLastView = (TextView) findViewById(R.id.dateLastView);

        //Setup Button and listener
        getDataButton = (Button) findViewById(R.id.getDataBtn);
        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data
                getProductInfo();

            }
        });

    }

    //Makes a toast displaying connection error
    public void errorNotificaton(String error) {
        Toast.makeText(CHFSActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    //Update information views
    public void updateInfoTextViews(ProductInfo prodInfo) {
        //implement method to take info from a class and display
        descView.setText(prodInfo.getDescription());
        barcodeView.setText(prodInfo.getBarcode());
        priceView.setText(prodInfo.getPrice());
        dateLastView.setText(prodInfo.getDatelastsold());
    }

    //Method to get the product info
    private void getSalesInfo() {
        //Need to add String argument to the above method when active

        /*
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", productCode)
                .addFormDataPart("start", startDate)
                .addFormDataPart("end", endDate)
                .build();
        */

        final Request request = new Request.Builder().url("http://192.168.1.5/testsales.php")
                .method("POST", RequestBody.create(null, new byte[0]))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        infoResult = "Failure!";
                        errorNotificaton(infoResult);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            infoResult = response.body().string();
                            salesFormatter.processSales(infoResult);
                            //method to update sales views
                        } catch (IOException ioe) {
                            infoResult = "Error during get body";
                            errorNotificaton(infoResult);
                        }
                    }
                });
            }
        });

    }


    //Method to get the product info
    private void getProductInfo() {
        //Need to add String argument to the above method when active

        //Change web address to getinfo.php and change below to multipart body request for product Id
        final Request request = new Request.Builder().url("http://192.168.1.5/testinfo.php")
                .method("POST", RequestBody.create(null, new byte[0]))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        infoResult = "Failure!";
                        errorNotificaton(infoResult);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            infoResult = response.body().string();
                            infoFormatter.processInfo(infoResult);
                            updateInfoTextViews(infoFormatter.getProductInfo());
                        } catch (IOException ioe) {
                            infoResult = "Error during get body";
                            errorNotificaton(infoResult);
                        }
                    }
                });
            }
        });

    }

}
