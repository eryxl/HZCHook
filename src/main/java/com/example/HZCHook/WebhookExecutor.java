// WebhookExecutor.java
package com.example.hzchook;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebhookExecutor {
    private final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public void sendWebhook(String url, Object data) {
        try {
            JSONObject payload = new JSONObject(data);
            
            RequestBody body = RequestBody.create(JSON, payload.toString());
            Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Content-Type", "application/json")
                .build();

            Response response = client.newCall(request).execute();
            
            if (!response.isSuccessful()) {
                Logger.getLogger(WebhookExecutor.class.getName())
                    .log(Level.SEVERE, "Failed to send webhook: " + response.code());
            }
        } catch (IOException ex) {
            Logger.getLogger(WebhookExecutor.class.getName())
                .log(Level.SEVERE, "Error sending webhook", ex);
        }
    }
}
