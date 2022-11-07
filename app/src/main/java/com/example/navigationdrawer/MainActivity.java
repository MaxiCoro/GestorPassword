package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.navigationdrawer.ExtraFragments.NewCardFragment;
import com.example.navigationdrawer.ExtraFragments.NewNoteFragment;
import com.example.navigationdrawer.ExtraFragments.NewPasswordFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton fabNewPassword;
    private FloatingActionButton fabNewCard;
    private FloatingActionButton fabNewNote;
    private FloatingActionButton fabOpen;
    private FragmentTransaction transaction;
    private boolean fabOpenIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Navigation
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Fabs
        fabOpen = findViewById(R.id.fab_Open);
        fabNewCard = findViewById(R.id.fab_Card);
        fabNewPassword = findViewById(R.id.fab_Password);
        fabNewNote = findViewById(R.id.fab_Note);
        fabOpenIsActive = false;

        fabOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCerrarFabs();
                accionBotones();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        // Create a transaction
        transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            // Create a new fragment of the appropriate type
            PasswordsFragment fragment = new PasswordsFragment();
            // What to do and where to do it
            transaction.replace(R.id.fragmentHolder, fragment);

        } else if (id == R.id.nav_home) {
            HomeFragment fragment = new HomeFragment();
            transaction.replace(R.id.fragmentHolder, fragment);

        } else if (id == R.id.nav_slideshow) {
            CardsFragment fragment = new CardsFragment();
            transaction.replace(R.id.fragmentHolder, fragment);

        }  else if (id == R.id.nav_supervisor) {
            NotesFragment fragment = new NotesFragment();
            transaction.replace(R.id.fragmentHolder, fragment);

        }

        // Ask Android to remember which
        // menu options the user has chosen
        transaction.addToBackStack(null);

        // Implement the change
        transaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);




        return true;
    }

    private void abrirCerrarFabs(){
        if(!fabOpenIsActive){
            fabNewPassword.setVisibility(View.VISIBLE);
            fabNewCard.setVisibility(View.VISIBLE);
            fabNewNote.setVisibility(View.VISIBLE);
            fabOpenIsActive = true;
        } else {
            fabNewPassword.setVisibility(View.INVISIBLE);
            fabNewCard.setVisibility(View.INVISIBLE);
            fabNewNote.setVisibility(View.INVISIBLE);
            fabOpenIsActive = false;
        }
    }

    private void accionBotones(){
        transaction = getSupportFragmentManager().beginTransaction();

        fabNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPasswordFragment fragment = new NewPasswordFragment();
                transaction.replace(R.id.fragmentHolder, fragment);
                transaction.commit();
                abrirCerrarFabs();
            }
        });

        fabNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewCardFragment fragment = new NewCardFragment();
                transaction.replace(R.id.fragmentHolder, fragment);
                transaction.commit();
                abrirCerrarFabs();
            }
        });

        fabNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewNoteFragment fragment = new NewNoteFragment();
                transaction.replace(R.id.fragmentHolder, fragment);
                transaction.commit();
                abrirCerrarFabs();
            }
        });

    }
}