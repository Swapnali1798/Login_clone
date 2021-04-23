package com.example.login_retrofit_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
private EditText e1,e2;
private Button b;
private String phone, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        e1=findViewById(R.id.edit1);
        e2=findViewById(R.id.edit2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=e1.getText().toString();
//                phone = Integer.parseInt( e1.getText().toString() );

                password=e2.getText().toString();
                String authToken=createAuthToken(phone,password);
                checkLoginDetails(authToken);

            }
        });
    }
    private void checkLoginDetails(String authToken) {
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        final Interface_api api=retrofit.create(Interface_api.class);
        Call<String> call=api.checkLogin(authToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              if (response.isSuccessful()){
                  if (response.body().matches("success")){
                      Toast.makeText(getApplicationContext(),"successfully Login",Toast.LENGTH_LONG).show();

                  }
                  else {
                      Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                  }
              }
              else {
                  //error
              }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();

            }
        });
    }
    private String createAuthToken(String phone,String password){
           byte[]data=new byte[0];
           try {
                data = (phone+ ":" + password).getBytes("UTF-8");
                 }
           catch (UnsupportedEncodingException e){
             e.printStackTrace();
                 }
        return "Basic" + Base64.encodeToString(data,Base64.NO_WRAP);

    }
}