package com.example.marcos.unaspwhatsapp_sqledition.PostActivity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcos.unaspwhatsapp_sqledition.Database.AlertDialogClass;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DB;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBNoticias;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DatabaseHelper;
import com.example.marcos.unaspwhatsapp_sqledition.MainActivity.MainActivity;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.sql.ResultSet;

public class Post extends AppCompatActivity {

    private EditText textPost;
    private Button btnPostar;
    private AlertDialogClass msgErro;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        textPost = findViewById(R.id.txt_post);
        btnPostar = findViewById(R.id.btn_postar);
        myDb = new DatabaseHelper(this);

    }

    public void salvarNoticia (View view){
        try {

            String criarNoticia = textPost.getText().toString();

            if (!(textPost.getText().toString().equals("") || textPost.getText() == null)){
                AddData(criarNoticia);
                Intent intent = new Intent(Post.this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                textPost.setError("Digite algo antes de enviar");
            }
        }
        catch (Exception e){
            msgErro.showText("Erro","erro: "+  e);
        }
    }

    public void AddData(String newEntry){
        boolean insertData = myDb.addData(newEntry);

        if (insertData==true){
            Toast.makeText(Post.this,"Sua notícia foi postada com sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Post.this,"Algo deu errado, tente novamente", Toast.LENGTH_LONG).show();
        }
    }
}
