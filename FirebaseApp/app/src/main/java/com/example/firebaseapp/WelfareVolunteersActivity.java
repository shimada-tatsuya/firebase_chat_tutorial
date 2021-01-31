package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelfareVolunteersActivity extends AppCompatActivity {

    //firebase auth
    FirebaseAuth firebaseAuth;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfarevolunteers);

        //Actionbar and its title
        actionBar = getSupportActionBar();
        actionBar.setTitle("チャット");

        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //allocation fragment transaction(default, on start)
        actionBar.setTitle("割り当て表");//change actionbar title
        AllocationFragment fragment1 = new AllocationFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content,fragment1,"");
        ft1.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //handle item clicks
                    switch (item.getItemId()){
                        case R.id.nav_allocation:
                            //allocation fragment transaction
                            actionBar.setTitle("割り当て表");//change actionbar title
                            AllocationFragment fragment1 = new AllocationFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content,fragment1,"");
                            ft1.commit();
                            return true;
                        case R.id.nav_chat:
                            //chat fragment transaction
                            actionBar.setTitle("チャット");//change actionbar title
                            ChatFragment fragment2 = new ChatFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content,fragment2,"");
                            ft2.commit();

                            return true;
//                        case R.id.nav_users:
//                            //users fragment transaction
//                            actionBar.setTitle("Users");//change actionbar title
//                            UsersFragment fragment3 = new UsersFragment();
//                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
//                            ft3.replace(R.id.content,fragment3,"");
//                            ft3.commit();
//                            return true;
                    }
                    return false;
                }
            };

    private void checkUserStatus(){
        //get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            //user is signed in stay here
            //set email of logged in user
            //mProfileTv.setText(user.getEmail());
        }else {
            //user not signed in, go to main activity
            startActivity(new Intent(WelfareVolunteersActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }



    @Override
    protected void onStart(){
        //check on start of app
        checkUserStatus();
        super.onStart();
    }

    /*inflate options menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflating menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*handle menu item clicks*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //get item id
        int id=item.getItemId();
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }

        return super.onOptionsItemSelected(item);

    }
}