package studbud.aether.com.stadbad7;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInScreen extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, LogInFragment.OnFragmentInteractionListener{
    public static boolean connected;
    FrameLayout fragmentspace;
    public static FirebaseAuth mAuth;
    public static FirebaseUser user;

    public static FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        fragmentspace = findViewById(R.id.fragmentspace);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else connected = false;

        if(connected)
        {

            mAuth = FirebaseAuth.getInstance();
             user = mAuth.getCurrentUser();


            mAuthListener = new FirebaseAuth.AuthStateListener()
            {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if( user!=null)
                    {
                        Toast.makeText( getApplicationContext() , "dfafaf", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent( getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));}

                    else getSupportFragmentManager().beginTransaction().replace(R.id.fragmentspace, new LogInFragment()).commit();
                    }
            };
        }
        else Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
