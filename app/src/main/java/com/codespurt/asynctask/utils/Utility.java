package com.codespurt.asynctask.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.codespurt.asynctask.model.Response;
import com.google.gson.Gson;

/**
 * Created by CodeSpurt on 04-08-2017.
 */

public class Utility {

    private Context context;

    public Utility(Context context) {
        this.context = context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public Response parseData(String data) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(data, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String filterData(String s) {
        String s2 = s.replace("\\n", "");
        s2 = s2.replace("\\r", "");
        s2 = s2.replace("%", "");
        s2 = s2.replace("^", "");
        s2 = s2.replace("#", "");
        s2 = s2.replace("[", "");
        s2 = s2.replace("]", "");
        s2 = s2.replace("'", "");
        return s2.trim();
    }
}