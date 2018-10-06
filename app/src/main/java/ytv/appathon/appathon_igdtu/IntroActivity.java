package ytv.appathon.appathon_igdtu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {


    //Google SignIn
    private FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.first_slide_background)
                        .buttonsColor(R.color.first_slide_buttons)
                        .image(R.drawable.ic_onetap)
                        .title("One tap Alert!")
                        .description("Send a notification to your custom contacts.")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("We provide solutions to make your life better.");
                    }
                }, "Made with Love"));

        addSlide(new SlideFragmentBuilder()
                .image(R.drawable.ic_live_location)
                .backgroundColor(R.color.second_slide_background)
                .buttonsColor(R.color.second_slide_buttons)
                .title("Instant Live Location Tracker!")
                .description("Send your location anytime, anywhere")
                .build());

//        addSlide(new CustomSlide());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.third_slide_background)
                .buttonsColor(R.color.third_slide_buttons)
//                        .possiblePermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS})
//                        .neededPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
                .image(R.drawable.ic_people)
                .title("Keep your closed ones close.")
                .description("Updates with in instant")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.ic_score)
                .title("Safety Score")
                .description("Checks the safety of locality before visiting")
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.custom_slide_background)
                .buttonsColor(R.color.google_signin_buttons)
                .image(R.drawable.ic_google_logo)
                .title("Sign In to continue.")
//                .description("checks the safety of locality before visiting")
                .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                }, "Sign in"));

        /**
         * Google SignIn Code.
         */
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {

                Toast.makeText(IntroActivity.this, user.getDisplayName() + user.getEmail() + user.getPhoneNumber() + "You're Successfully Signed In", Toast.LENGTH_SHORT).show();


                setAppLaunch(false);
//                settingUserInfo(user.getDisplayName(), user.getEmail(), Objects.requireNonNull(user.getPhotoUrl()).toString())
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            } else {
               Toast.makeText(getApplicationContext(), "Sign-in to continue.", Toast.LENGTH_SHORT).show();
            }

        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    @Override
    public void onFinish() {
        super.onFinish();
        startActivity(new Intent(this, MainActivity.class));
        setAppLaunch(false);
        finish();
    }

    public void setAppLaunch(boolean b) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppLaunch", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("finish", b);
        editor.apply();
    }


    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            if (resultCode == RESULT_CANCELED) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
//                Snackbar.make(findViewById(R.id.signin_layout), "Sign-in to continue.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

//        progressBarr.setVisibility(View.VISIBLE);
//        button.setVisibility(View.GONE);


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

//                            progressBarr.setVisibility(View.GONE);
//                            button.setVisibility(View.VISIBLE);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(IntroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}

