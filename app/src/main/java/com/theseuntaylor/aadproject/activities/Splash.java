package com.theseuntaylor.aadproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import com.theseuntaylor.aadproject.R;

public class Splash extends AppCompatActivity {

    Handler handler = new Handler();
    private final int SPLASH_DURATION = 4000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler.postDelayed(new SplashHandler(), SPLASH_DURATION);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    class SplashHandler implements Runnable{
        public void run(){
            Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
