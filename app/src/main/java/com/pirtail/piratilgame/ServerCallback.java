package com.pirtail.piratilgame;

import org.json.JSONException;
import org.json.JSONObject;


public interface ServerCallback {
    void onSuccess(JSONObject result) throws JSONException;
}
