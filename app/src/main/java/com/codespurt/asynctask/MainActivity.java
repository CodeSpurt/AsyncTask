package com.codespurt.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codespurt.asynctask.async.HttpGetRequest;
import com.codespurt.asynctask.async.Urls;
import com.codespurt.asynctask.engine.HttpGetData;
import com.codespurt.asynctask.model.Response;
import com.codespurt.asynctask.utils.Utility;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView data;

    private Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        data = (TextView) findViewById(R.id.tv_data);

        utility = new Utility(this);

        if (utility.isInternetAvailable()) {
            // hit service using asynctask
            hitService();
        } else {
            progressBar.setVisibility(View.GONE);
            data.setVisibility(View.VISIBLE);
            data.setText(getResources().getString(R.string.no_internet_connection));
        }
    }

    private void hitService() {
        HttpGetRequest request = new HttpGetRequest(httpGetData);
        request.execute(new Urls().getURL());
    }

    HttpGetData httpGetData = new HttpGetData() {

        @Override
        public void getData(String s) {
            Response parsedData = utility.parseData(s);
            progressBar.setVisibility(View.GONE);
            data.setVisibility(View.VISIBLE);

            if (parsedData != null) {
                Log.d("CheckResponse", utility.filterData(parsedData.getHoroscope()));
                data.setText("Data get successful");
            } else {
                data.setText(getResources().getString(R.string.no_internet_connection));
            }
        }
    };
}