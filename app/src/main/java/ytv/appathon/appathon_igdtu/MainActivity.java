package ytv.appathon.appathon_igdtu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolBarText)
    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();

        initSafetyTip();
    }

    private void init() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        toolbarText.setText(getResources().getString(R.string.app_name));
    }

    public void initSafetyTip(){

        String safteyTip[] = SafteyTipsFetch.returnDetails();

        new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.colorPrimary)
                .setButtonsColorRes(R.color.colorAccent)
              //TODO add the app icon  .setIcon(R.drawable.ic_star_border_white_36dp)
                .setTitle(safteyTip[0])
                .setMessage(safteyTip[1])
                .setPositiveButton("Hide Tips", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setNegativeButton("Show more Tips", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSafetyTip();
                    }
                })
                .show();
    }

}
