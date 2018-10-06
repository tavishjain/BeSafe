package ytv.appathon.appathon_igdtu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        if(getAppLaunch()){
            startActivity(new Intent(SplashScreen.this, IntroActivity.class));
            finish();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }, 1000);
        }
    }


    public boolean getAppLaunch() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("finish", true);
    }
}
