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
public class MainActivityFragment extends Fragment {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView registerScreen = (TextView) rootView.findViewById(R.id.link_to_register);
        registerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        inputEmail = (EditText) rootView.findViewById(R.id.email);
        inputPassword = (EditText) rootView.findViewById(R.id.password);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);

        pDialog=new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        db=new SQLiteHandler(getActivity().getApplicationContext());

        session=new SessionManager(getActivity().getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString().trim();
                String password=inputPassword.getText().toString().trim();
                if(!email.isEmpty()&&!password.isEmpty()){
                    checkLogin(email, password);
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please enter your Login information!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return rootView;
    }

    private void checkLogin(final String email,final String password) {
        String tag_string_req = "req_login";
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq=new StringRequest(Request.Method.POST, Configuration.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject obj = new JSONObject(response);
                    int result = obj.getInt("result");
                    if (result == 1) {
                        session.setLogin(true);
                        String username=obj.getString("username");
                        String email=obj.getString("email");
                        String phone=obj.getString("phone");
                        db.addUser(username,email,phone);

                        Intent intent = new Intent(getActivity().getApplicationContext(), WelcomeActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid Login, try again!", Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }){
            @Override
        protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put("username",email);
                params.put("password",password);
                return params;

            }
        };
        Controller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    private void showDialog(){
        if(!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog(){
        if(pDialog.isShowing())
            pDialog.dismiss();
    }
}
