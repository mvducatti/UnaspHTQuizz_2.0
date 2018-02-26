package com.example.marcos.unaspwhatsapp_sqledition.MainActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.marcos.unaspwhatsapp_sqledition.Database.DB;
import com.example.marcos.unaspwhatsapp_sqledition.Database.DBUsuario;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.sql.ResultSet;

import javax.security.auth.login.LoginException;

public class Login extends AppCompatActivity {

    private EditText editLogin, editSenha;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        editLogin = findViewById(R.id.loginText);
        editSenha = findViewById(R.id.SenhaText);
    }

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void login(View view){
        ResultSet rs;

        try {
            String testelogin = editLogin.getText().toString();
            String testesenha = editSenha.getText().toString();
            rs = DB.execute("SELECT login, senha FROM usuario WHERE login = '" + testelogin + "' AND senha = '" + testesenha + "'");
            while (rs.next()){
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                if (login.equals(testelogin)){
                    if (senha.equals(testesenha)){
                        DBUsuario.usuarioLogado = Integer.parseInt(rs.getString("id_usuario"));
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        return;
                    }
                }
            }
            throw new LoginException("Usuário ou senha incorretos");
        }catch (LoginException e){
            exibirTexto("Erro de Login", e.getMessage());
        }
        catch (Exception e) {
            exibirTexto("Erro", e.getMessage());
        }
    }


    public void criarUsuario(View view){
        try {
            Intent intent = new Intent(this, CadastrarUsuario.class);
            startActivity(intent);
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }
}