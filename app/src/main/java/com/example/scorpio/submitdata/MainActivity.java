package com.example.scorpio.submitdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scorpio.submitdata.utils.NetUtils;
import com.example.scorpio.submitdata.utils.NetUtils2;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;
    private static  final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
    }
    
    /*使用HttpClient方式提交get请求*/
    public void doHttpClientOfGet(View v){
        Log.i(TAG,"doHttpClientOfGet");
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                final String  state = NetUtils2.loginOfGet(username,password);
                //执行任务在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //就是在主线程中操作
                        Toast.makeText(MainActivity.this,state,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
    
    /*使用HttpClient方式提交post请求*/
    public void doHttpClientOfPost(View v){
        Log.i(TAG,"doHttpClientOfPost");
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                final String  state = NetUtils2.loginOfPost(username,password);
                //执行任务在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //就是在主线程中操作
                        Toast.makeText(MainActivity.this,state,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
    
    public void doGet(View v){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用get方式抓取数据
                final String state = NetUtils.loginOfGet(username,password);
                
                //执行任务在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //就是在主线程中操作
                        Toast.makeText(MainActivity.this,state,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public void doPost(View v){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用get方式抓取数据
                final String state = NetUtils.loginOfPost(username,password);

                //执行任务在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //就是在主线程中操作
                        Toast.makeText(MainActivity.this,state,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
