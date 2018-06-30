package com.example.q.project1;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Vibrator vibrator = null;
    private TextView mTextMessage;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    private  static Fragment defaultFrag = new ReadContact();
    private static Fragment galleryFrag = new gallery();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            contextOfApplication = getApplicationContext();

            switch (item.getItemId()) {
                case R.id.navigation_contact:
                    replaceFragment(defaultFrag);
                    vibrator.vibrate(50);
                    return true;
                case R.id.navigation_gallery:
                    replaceFragment(galleryFrag);
                    vibrator.vibrate(50);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(defaultFrag);
                    vibrator.vibrate(50);
                    return true;
            }
            return false;
        }
    };
    //permission handling

    private void replaceFragment(Fragment frag){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.message, frag).commit();
    }

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        if(hasPermissions(this, PERMISSIONS)) {
            Toast.makeText(MainActivity.this,"you have permissions!",Toast.LENGTH_SHORT).show();
            replaceFragment(defaultFrag);
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}