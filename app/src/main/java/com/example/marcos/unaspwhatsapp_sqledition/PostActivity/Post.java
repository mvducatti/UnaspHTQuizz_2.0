package com.example.marcos.unaspwhatsapp_sqledition.PostActivity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marcos.unaspwhatsapp_sqledition.Database.AlertDialogClass;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DB;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBNoticias;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.sql.ResultSet;

public class Post extends AppCompatActivity {

    private EditText textPost;
    private Button btnPostar;
    private AlertDialogClass msgErro;
    private DBNoticias dbNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        textPost = findViewById(R.id.txt_post);
        btnPostar = findViewById(R.id.btn_postar);

    }

    public void salvarNoticia (View view){
        ResultSet rs;
        try {

            Snackbar.make(view, "Notícia postada com sucesso!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
//            String criarNoticia = textPost.getText().toString();
//
//            if (!(textPost.getText().toString().equals("") || textPost.getText() == null)){
////                DB.update("INSERT INTO curso (nomedocurso) VALUES ('" + criarNoticia + "')");
//
//            }
        }
        catch (Exception e){
            msgErro.showText("Erro","erro: "+  e);
        }
    }

    public void cancelarcurso (View view){
        finish();
    }
}
