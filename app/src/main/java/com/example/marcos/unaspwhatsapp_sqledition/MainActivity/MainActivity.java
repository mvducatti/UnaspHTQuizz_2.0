package com.example.marcos.unaspwhatsapp_sqledition.MainActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.marcos.unaspwhatsapp_sqledition.Database.AlertDialogClass;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DB;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBNoticias;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBUsuario;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AlertDialogClass msgerro;
    private DBNoticias dbNoticias;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbNoticias = new DBNoticias();
        listView = findViewById(R.id.listView);

        toolbar = findViewById((R.id.toolbar));
        toolbar.setTitle("Notícias UNASP-HT");
        setSupportActionBar(toolbar);

        postarNoticia();

    }

    @Override
    //Escolher itens selecionados na toolbar
    public boolean onOptionsItemSelected(MenuItem item) {

        //Selecionando o item e as suas ações
        switch (item.getItemId()) {
            case R.id.item_adicionar:
                abrirCadastroNoticia();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroNoticia() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Nova mensagem");
        alertDialog.setMessage("Por favor digite a sua mensagem abaixo:");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(this);
        alertDialog.setView(editText);

//        do {
//            editText.setError("Por favor colocar mais conteúdo antes de postar");
//        } while (editText.getText().toString().length() < 15);


        alertDialog.setPositiveButton("Postar Notícia", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {

                    //Recuperar o que o usuário escreveu no editText
                    String postdaNoticia = editText.getText().toString();

                    if (postdaNoticia.isEmpty()) {

                        msgerro.showText("", "Por favor colocar mais conteúdo antes de postar");

                    } else {

                        dbNoticias.setPost(postdaNoticia);
                        dbNoticias.salvar(DBUsuario.usuarioLogado);
                        postarNoticia();
                    }
                } catch (Exception e) {
                    msgerro.showText("Erro", "erro: " + e);
                }

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    public void postarNoticia(){

        try {
            ResultSet resultSet = DB.select("SELECT * FROM noticia");

            ArrayList<String> postNoticia = new ArrayList<>();

            while (resultSet.next()){
                postNoticia.add(resultSet.getString("noticias"));
            }

            listView.setAdapter(new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    postNoticia
            ));

        }catch (Exception e){
            msgerro.showText("erro", "erro: "+e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        postarNoticia();
    }
}
