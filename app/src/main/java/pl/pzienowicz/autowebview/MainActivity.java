package pl.pzienowicz.autowebview;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private WebView myWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getActionBar();
        if(ab != null) {
            ab.hide();
        }

        if(BuildConfig.ENABLE_DEBUG_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }

        myWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setJavaScriptEnabled(BuildConfig.ENABLE_JS);
        webSettings.setAllowFileAccess(BuildConfig.ALLOW_FILE_ACCESS);

        if(BuildConfig.USE_AUTH) {
            myWebView.setHttpAuthUsernamePassword(BuildConfig.HOST, "", BuildConfig.USERNAME, BuildConfig.PASSWORD);
        }

        myWebView.setWebViewClient(new MyWebViewClient ());
        myWebView.loadUrl(BuildConfig.URL);

        Timer timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();

        if(BuildConfig.REFRESH_PERIOD > 0) {
            timer.schedule(myTimerTask, BuildConfig.REFRESH_DELAY, BuildConfig.REFRESH_PERIOD);
        } else {
            timer.schedule(myTimerTask, BuildConfig.REFRESH_DELAY);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            if(BuildConfig.USE_AUTH) {
                handler.proceed(BuildConfig.USERNAME, BuildConfig.PASSWORD);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.scrollBy(BuildConfig.SCROLL_X, BuildConfig.SCROLL_Y);
        }
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    myWebView.reload();
                }});
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(myWebView.canGoBack()){
                        myWebView.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
