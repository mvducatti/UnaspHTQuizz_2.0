package com.example.marcos.unaspwhatsapp_sqledition.MainActivity;

import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.marcos.unaspwhatsapp_sqledition.PostActivity.Post;
import com.example.marcos.unaspwhatsapp_sqledition.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
=======
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.marcos.unaspwhatsapp_sqledition.Adapter.NewsRecyclerAdapter;
import com.example.marcos.unaspwhatsapp_sqledition.Adapter.UsersRecyclerAdapter;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBNoticias;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBUsuario;
import com.example.marcos.unaspwhatsapp_sqledition.Main2Activity;
import com.example.marcos.unaspwhatsapp_sqledition.Model.Noticia;
import com.example.marcos.unaspwhatsapp_sqledition.Model.User;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatActivity activity = MainActivity.this;
    private RecyclerView recyclerViewNews;
    private List<Noticia> listNoticias;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private DBNoticias databaseHelper;
>>>>>>> new-branch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Post.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
=======

        FloatingActionButton fab = findViewById(R.id.fabNews);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostNews.class);
                startActivity(intent);

                initViews();
                initObjects();
            }
        });

    }

    private void initViews() {
        recyclerViewNews = findViewById(R.id.recyclerViewNews);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {

        try {
            listNoticias = new ArrayList<>();
            newsRecyclerAdapter = new NewsRecyclerAdapter(listNoticias);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewNews.setLayoutManager(mLayoutManager);
            recyclerViewNews.setItemAnimator(new DefaultItemAnimator());
            recyclerViewNews.setHasFixedSize(true);
            recyclerViewNews.setAdapter(newsRecyclerAdapter);
            databaseHelper = new DBNoticias(activity);

            getDataFromSQLite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listNoticias.clear();
                for (DBNoticias dbNoticias : databaseHelper.getLista()) {
                    Noticia noticia = new Noticia();
                    noticia.setUser_id(dbNoticias.getId());
                    noticia.setNewsTitle(dbNoticias.getNewsTitle());
                    noticia.setNewsMessage(dbNoticias.getNewsPost());
                    listNoticias.add(noticia);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                newsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
>>>>>>> new-branch
    }
}
