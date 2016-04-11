package com.example.scorpio.submitdata;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.net.URLEncoder;

public class MainActivity2 extends Activity {

    private EditText etUserName;
    private EditText etPassword;
    protected static final String TAG ="MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
    }
    
    public void doGet(View v){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();

        String data = "username=" + URLEncoder.encode(username) + "&password=" + URLEncoder.encode(password);

        client.get("http://10.0.2.2:8080/ServerScorpio/servlet/LoginServlet?" + data, new MyResponseHandler());
    }

    public void doPost(View v){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("password",password);
        
        client.post("http://10.0.2.2:8080/ServerScorpio/servlet/LoginServlet",params,new MyResponseHandler());
    }
    
    class MyResponseHandler extends AsyncHttpResponseHandler{

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
          //  Log.i(TAG,"statusCode："+statusCode);
            Toast.makeText(MainActivity2.this,"成功："+statusCode+",body："+new String(responseBody),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(MainActivity2.this,"失败："+statusCode+",body："+new String(responseBody),Toast.LENGTH_SHORT).show();

        }
    }
}
