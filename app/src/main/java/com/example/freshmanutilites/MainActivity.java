package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// for the fragment to redirect (intent ) you to other feature of the project like home-profile-post-request-...
public class MainActivity extends AppCompatActivity {

    // firebase authentication variable
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // firebaseAuth.getInstance() - from firebase manager
        mAuth = FirebaseAuth.getInstance();
        // object of bottom nev view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setBackgroundColor(Color.YELLOW);

        // what will happen if we select an item from the bottom nav menu
        bottomNavigationView.setOnNavigationItemSelectedListener(onNav);

        //set a default fragment for the frame layout we created in the activity_main.xml
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment1()).commit();

    }

        // object creation .. just did what got from the net
    private BottomNavigationView.OnNavigationItemSelectedListener onNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // selected fragment if null
            Fragment selectedFragment = null;

            // selected fragment based on if elese and the id of the bottom_nav menu
            if(item.getItemId()==R.id.profile_bottom_menu_item){


                selectedFragment = new Fragment1();
            }
            if(item.getItemId()==R.id.ask_bottom_menu_item){
                selectedFragment = new Fragment2();
            }
            if(item.getItemId()==R.id.request_bottom_menu_item){
                selectedFragment = new Fragment3();
            }
            if(item.getItemId()==R.id.home_bottom_menu_item){
                selectedFragment = new Fragment4();
            }
            // this line is for attaching the fragment to a layout
            // fragment  - subActivity
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();
            // return a thing - return true
            return true;
        }
    };


    /***
    // for at this stage of the project we built that button for test purposes only
     public void logout(View view){
    // method which works from singOut
     mAuth.signOut();

     Intent intent = new Intent(MainActivity.this,LoginActivity.class);
     startActivity(intent);
     }
***/

     // activity lifecycle related
    @Override
    protected void onStart() {
        super.onStart();

        // get the current user identity from the firebase using that method
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // if user doesn't exist it will start from the begin where RegistrationActivity will work
        if(user==null){
            // intent
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
