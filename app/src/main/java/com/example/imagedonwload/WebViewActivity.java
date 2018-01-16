package com.example.imagedonwload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WebViewActivity extends AppCompatActivity {

    Button wbload;
    EditText wzText;
    String wangzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wbload = (Button) findViewById(R.id.webview_load);
        wzText = (EditText) findViewById(R.id.wangzhi);
        if (wangzhi == null){
            wzText.setText("https://m.baidu.com");
        }
        wbload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this,MainActivity.class);
                wangzhi = wzText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("address",wangzhi);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
