package ytv.appathon.appathon_igdtu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import android.view.View;

import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.LimitColumn;
import com.wafflecopter.multicontactpicker.MultiContactPicker;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolBarText)
    TextView toolbarText;

    private GoogleMap googleMap;
    private LocationManager locationManager;

    private double latitude, longitude;

    private static final int CONTACT_PICKER_REQUEST = 991;
//    private ArrayList<ContactResult> results = new ArrayList<>();

    private SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        smsManager = SmsManager.getDefault();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Dexter.withActivity(MainActivity.this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            try {
                                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                        1000, 0,
                                        MainActivity.this);
                            } catch (SecurityException e) {
                                e.printStackTrace();
                            }
                            init();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Location Permission Required",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }

                }).check();
        init();
        initSafetyTip();

    }

    @OnClick(R.id.btnSelContacts)
    public void selectContacts() {
        new MultiContactPicker.Builder(MainActivity.this) //Activity/fragment context
                .theme(R.style.MyCustomPickerTheme) //Optional - default: MultiContactPicker.Azure
                .hideScrollbar(false) //Optional - default: false
                .showTrack(true) //Optional - default: true
                .searchIconColor(Color.WHITE) //Option - default: White
                .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                .handleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                .bubbleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                .bubbleTextColor(Color.WHITE) //Optional - default: White
                .setTitleText("Select Contacts") //Optional - default: Select Contacts
//                        .setSelectedContacts("10", "5" / myList) //Optional - will pre-select contacts of your choice. String... or List<ContactResult>
                .setLoadingType(MultiContactPicker.LOAD_ASYNC) //Optional - default LOAD_ASYNC (wait till all loaded vs stream results)
                .limitToColumn(LimitColumn.NONE) //Optional - default NONE (Include phone + email, limiting to one can improve loading time)
                .setActivityAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out) //Optional - default: No animation overrides
                .showPickerForResult(CONTACT_PICKER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                List<ContactResult> results = MultiContactPicker.obtainResult(data);
                Log.d("MyTag", results.get(0).getDisplayName());
            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("User closed the picker without selecting items.");
            }
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        toolbarText.setText(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.d("Latitude is", String.valueOf(latitude));
        Log.d("Longitude is", String.valueOf(longitude));

        LatLng currentLoc = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(currentLoc));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 15.0f));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @OnClick(R.id.btnCallPolice)
    public void callNearbyPolice() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1091"));
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void initSafetyTip() {

        String safetyTip[] = SafetyTipsFetch.returnDetails();

        new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.colorPrimary)
                .setButtonsColorRes(R.color.colorAccent)
                .setIcon(R.drawable.ic_dialog_box_icon)
                .setTitle(safetyTip[0])
                .setMessage(safetyTip[1])
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

    @OnClick(R.id.btnStatus)
    public void sendStatusUpdateMsg() {
        smsManager.sendTextMessage(
                "+919811392201",
                "9899061938",
                getResources().getString(R.string.help_msg) + "\n" +
                        "https://www.google.com/maps/search/?api=1&query="
                        + String.valueOf(latitude) + "," + String.valueOf(longitude),
                null,
                null
        );
        Toast.makeText(MainActivity.this,
                "Status update sent",
                Toast.LENGTH_SHORT).show();
    }

}