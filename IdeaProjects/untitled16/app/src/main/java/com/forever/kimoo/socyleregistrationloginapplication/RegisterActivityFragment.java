package com.forever.kimoo.socyleregistrationloginapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import helper.SQLiteHandler;
import helper.SessionManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterActivityFragment extends Fragment {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputPhone;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    public RegisterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        TextView loginScreen = (TextView) rootView.findViewById(R.id.link_to_login);
        loginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        inputFullName = (EditText) rootView.findViewById(R.id.reg_fullname);
        inputEmail = (EditText) rootView.findViewById(R.id.reg_email);
        inputPassword = (EditText) rootView.findViewById(R.id.reg_password);
        inputPhone = (EditText) rootView.findViewById(R.id.inputPhone);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        session = new SessionManager(getActivity().getApplicationContext());

        db = new SQLiteHandler(getActivity().getApplicationContext());

        if (session.isLoggedIn()) {
            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
            startActivity(intent);

        }
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();
                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !phone.isEmpty()) {
                    registerUser(username, email, password, phone);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter missing fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

            private void registerUser(final String username, final String email, final String password, final String phone) {
                String tag_string_req = "req_register";
                pDialog.setMessage("Registering...");
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST, Configuration.URL_REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        hideDialog();

                        try {
                            JSONObject obj = new JSONObject(response);
                            int result = obj.getInt("result");
                            if (result == 1) {
                                String username = obj.getString("username");
                                String email = obj.getString("email");
                                String phone = obj.getString("phone");

                                db.addUser(username, email, phone);
                                Toast.makeText(getActivity().getApplicationContext(), "User successfully registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Error !", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getActivity().getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("username",username);
                        params.put("email",email);
                        params.put("password",password);
                        params.put("phone",phone);
                        return params;
                    }
                };
                Controller.getInstance().addToRequestQueue(strReq,tag_string_req);
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
