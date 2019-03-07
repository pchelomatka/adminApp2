package com.example.adminapp.addPoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adminapp.R;
import com.example.adminapp.interfaces.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPoint extends AppCompatActivity implements View.OnClickListener {

    //public static String country;
    Button buttonAddPoint;
    EditText editTextDeviceId;
    EditText editTextTitle;
    EditText editTextBuildingId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_point);

        buttonAddPoint = findViewById(R.id.button2);
        editTextDeviceId = findViewById(R.id.editText5);
        editTextTitle = findViewById(R.id.editText6);
        editTextBuildingId = findViewById(R.id.editText7);
        buttonAddPoint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.81.2.251")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String deviceId = editTextDeviceId.getText().toString().trim();
        String title = editTextTitle.getText().toString().trim();
        String buildingId = editTextBuildingId.getText().toString().trim();

        final AddPointBody addPointBody = new AddPointBody(deviceId, title, buildingId);

        API api = retrofit.create(API.class);
        api.addPoint(addPointBody);

        Call<AddPointResponse> call = api.addPoint(addPointBody);
        call.enqueue(new Callback<AddPointResponse>() {
            @Override
            public void onResponse(Call<AddPointResponse> call, Response<AddPointResponse> response) {
                if(response.isSuccessful()) {
                    Log.i("STATUS", String.valueOf(response.body().getResponse()));
                }
            }

            @Override
            public void onFailure(Call<AddPointResponse> call, Throwable t) {

            }
        });

    }
}
