package com.example.simpleplayer;

import org.json.JSONException;

public interface VolleyCallback {
    void onSuccessResponse(String result) throws JSONException;
}