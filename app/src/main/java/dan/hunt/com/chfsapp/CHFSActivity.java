package dan.hunt.com.chfsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        //Setup Button and listener
        getDataButton = (Button) findViewById(R.id.getDataBtn);
        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data
             getWebservice();

            }
        });

        //Setup textviews
        descView = (TextView) findViewById(R.id.descView);
        barcodeView = (TextView) findViewById(R.id.barcodeView);
        priceView = (TextView) findViewById(R.id.priceView);
        dateLastView = (TextView) findViewById(R.id.dateLastView);

    }

    //Method to get the product info
    private void getWebservice() {
        //Need to add String argument to the above method when active

        /*
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", productCode)
                .build();
        */

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
                            updateTextViews();
                        } catch (IOException ioe) {
                            infoResult = "Error during get body";
                            updateTextViews();
                        }
                    }
                });
            }
        });

    }

    //Method to update the textview
    public void updateTextViews() {
        descView.setText(infoResult);
    }

    //Update information views
    public void updateInfoTextViews() {
        //implement method to take info from a class and display
    }
    //Method to get the sales info
}
