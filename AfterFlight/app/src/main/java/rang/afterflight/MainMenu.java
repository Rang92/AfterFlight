package rang.afterflight;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.parse.ParseUser;
import rang.afterflight.fragments.DemandFragment;
import rang.afterflight.fragments.MainFragment;
import rang.afterflight.fragments.MyPostsFragment;
import rang.afterflight.fragments.SettingsFragment;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = this.getIntent();
        String username = intent.getStringExtra("username");

        final TextView user = (TextView) findViewById(R.id.username_nav);
        user.setText(username);

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = new rang.afterflight.fragments.MainFragment();
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.content_main, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // get parse profile picture

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
            fm.beginTransaction().replace(R.id.content_main, new MainFragment()).commit();
        } else if (id == R.id.nav_demand) {
            fm.beginTransaction().replace(R.id.content_main, new DemandFragment()).commit();
        } else if (id == R.id.nav_posts) {
            fm.beginTransaction().replace(R.id.content_main, new MyPostsFragment()).commit();
        }  else if (id == R.id.nav_settings) {
            fm.beginTransaction().replace(R.id.content_main, new SettingsFragment()).commit();
        } else if (id == R.id.nav_logout) {
//            ParseUser.logOut();
//            Intent back = new Intent(this, LoginActivity.class);
//            startActivity(back);
//            finish();
            showExitDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showExitDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("NO", null)
                .setNeutralButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();
    }
}