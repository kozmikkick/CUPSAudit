package distantfantasy.cups;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import helper.SQLiteHandler;
import helper.SessionManager;
import app.AppController;
import app.AppConfig;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btn_link_login;
    private EditText inputPasscode;
    private EditText inputName;
    private EditText inputUserName;
    private EditText inputAreaEmail;
    private EditText inputStoreNumber;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputPasscode = (EditText) findViewById(R.id.passcode);
        inputName = (EditText) findViewById(R.id.signup_input_name);
        inputUserName = (EditText) findViewById(R.id.signup_input_username);
        inputAreaEmail = (EditText) findViewById(R.id.signup_input_areaemail);
        inputEmail = (EditText) findViewById(R.id.signup_input_email);
        inputStoreNumber = (EditText) findViewById(R.id.signup_input_storenumber);
        inputPassword = (EditText) findViewById(R.id.signup_input_password);
        btnRegister = (Button) findViewById(R.id.btn_signup);
        btn_link_login = (Button) findViewById(R.id.btn_link_login);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Logout User
            session.setLogin(false);

            db.deleteUsers();


        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String passcode = inputPasscode.getText().toString();
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String areaemail = inputAreaEmail.getText().toString();
                String storenumber = inputStoreNumber.getText().toString();
                String username = inputUserName.getText().toString();


                if (!passcode.isEmpty() && !name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !areaemail.isEmpty() && !storenumber.isEmpty()) {
                    registerUser(passcode, name, email, areaemail, storenumber, username, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btn_link_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String passcode, final String name, final String email, final String areaemail, final String storenumber, final String username, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        JSONObject user = jObj.getJSONObject("user");
                        String uid = user.getString("uid");
                        String name = user.getString("name");
                        String areaemail = user.getString("areaemail");
                        String storenumber = user.getString("storenumber");

                        String reguser = " " + uid + " " + name + " " + areaemail + " " + storenumber;

                        // Inserting row in users table
                        db.addUser(name, areaemail, uid, storenumber);
                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Inserting row in users table
                        Log.d(TAG, "Loading User: " + reguser);

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("passcode", passcode);
                params.put("name", name);
                params.put("email", email);
                params.put("areaemail", areaemail);
                params.put("storenumber", storenumber);
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
