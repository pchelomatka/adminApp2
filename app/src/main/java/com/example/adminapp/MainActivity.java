package com.example.adminapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.authorization.RegistrationBody;
import com.example.adminapp.authorization.RegistrationResponse;
import com.example.adminapp.interfaces.API;
import com.example.adminapp.addPoint.AddPoint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin;
    EditText editTextLogin;
    EditText editTextPass;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(this);
        editTextLogin = findViewById(R.id.editText);
        editTextPass = findViewById(R.id.editText2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String login = editTextLogin.getText().toString().trim();
                String password = editTextPass.getText().toString().trim();

                if (password.isEmpty()) {
                    editTextPass.setError("Password required");
                    editTextPass.requestFocus();
                }

                if (login.isEmpty()) {
                    editTextLogin.setError("Login required");
                    editTextLogin.requestFocus();
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.81.2.251")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final RegistrationBody registrationBody = new RegistrationBody(login, MD5(password));


                API api = retrofit.create(API.class);
                api.loginUser(registrationBody);

                Call<RegistrationResponse> call = api.loginUser(registrationBody);

                call.enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> responseBody) {
                        if (responseBody.isSuccessful()) {
                            token = responseBody.body().response.toString();
                            token = (token.substring(0, token.lastIndexOf('}'))).substring(token.lastIndexOf('=') + 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
                    }
                });
                Intent intent = new Intent(this, AddPoint.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    public String MD5(String md5) {
        try {
            md5 = "ufo" + md5 + "ufo";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
}
