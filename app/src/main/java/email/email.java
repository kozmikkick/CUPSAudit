package email;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.AppConfig;
import app.AppController;

/**
 * Created by kozmikkick on 7/1/17.
 */

public class email {
    private static final String TAG = email.class.getSimpleName();

    public void sendingemail(final Context context,final String storeemail,final String areaemail,final String subject,final String filename,final String body){
        // Tag used to cancel the request
        String tag_string_req = "req_email";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EMAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Email Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite

                        Toast.makeText(context, "Email successfully send.", Toast.LENGTH_LONG).show();

                        // Launch CUPSAudit History in Future...

                    } else {

                        // Error occurred in registration. Get the error
                        // message

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Email Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "email");
                params.put("storeemail", storeemail);
                params.put("areaemail", areaemail);
                params.put("subject", subject);
                params.put("filename", filename);
                params.put("body", body);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
