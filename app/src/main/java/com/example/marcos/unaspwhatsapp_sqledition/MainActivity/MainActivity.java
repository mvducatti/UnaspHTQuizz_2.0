package com.example.marcos.unaspwhatsapp_sqledition.MainActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.marcos.unaspwhatsapp_sqledition.Adapter.NewsRecyclerAdapter;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBNoticias;
import com.example.marcos.unaspwhatsapp_sqledition.Model.Noticia;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatActivity activity = MainActivity.this;
    private RecyclerView recyclerViewNews;
    private List<Noticia> listNoticias;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private DBNoticias databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
