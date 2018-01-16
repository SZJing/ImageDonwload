package com.example.imagedonwload;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button mImageLoad;
    private WebView webView;
    private String List[];
    private String Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        Address = bundle.getString("address");
        mImageLoad = (Button) findViewById(R.id.load);
        mImageLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageLoadActivity.class);
                intent.putExtra("list",List);
                startActivity(intent);
            }
        });

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(MainActivity.this), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(Address);
    }

    public void addLocalJs() {
        webView.loadUrl("javascript:(function(){ " + "var objs = document.getElementsByTagName(\"img\");"
                + " var array=new Array(); " + " for(var j=0;j<objs.length;j++){ " + "array[j]=objs[j].src;" + " }  "
                + "window.imagelistner.getImage(array);   })()");
    }

    private class JsInterface {
        private Context context;

        public JsInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void getImage(String[] urls) {

            Log.i("chen", "========进入js方法========");

            if (urls != null && urls.length > 0) {
                for (int i = 0; i < urls.length; i++) {
                    Log.i("===" + i + "===", urls[i]);
                }
            }
            List = urls;
        }
    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            Log.i("chen", "加载完成-----" + url);
            view.getSettings().setBlockNetworkImage(false);
            addLocalJs();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
        return true;
    }
}
