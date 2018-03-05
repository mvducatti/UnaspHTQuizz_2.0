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
import com.example.marcos.unaspwhatsapp_sqledition.Main2Activity;
import com.example.marcos.unaspwhatsapp_sqledition.R;

import java.sql.ResultSet;

import javax.security.auth.login.LoginException;

public class Login extends AppCompatActivity {

    private EditText editEmail, editSenha;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        editEmail = findViewById(R.id.loginText);
        editSenha = findViewById(R.id.SenhaText);

        editEmail.setText("mvducatti@gmail.com");
        editSenha.setText("roketpower");
    }

    public void alert(String titulo, String txt){
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
            String testeemail = editEmail.getText().toString();
            String testesenha = editSenha.getText().toString();

            rs = DB.execute("SELECT user_email, user_password FROM newuser WHERE user_email = '" + testeemail + "' AND user_password = '" + testesenha + "'");
            while (rs.next()){
                String login = rs.getString("user_email");
                String senha = rs.getString("user_password");
                if (login.equals(testeemail)){
                    if (senha.equals(testesenha)){
                        Intent intent = new Intent(Login.this, Main2Activity.class);
                        intent.putExtra("LOGIN", editEmail.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        return;
                    }
                    else if (login != testelogin){
                        editLogin.setError("O e-mail é inválido");
                    }
                    else if (senha != testesenha){
                        editSenha.setError("Senha incorreta");
                    }
                }
            }
            throw new LoginException("Usuário ou senha incorretos");
        }catch (LoginException e){
            alert("Erro de Login", e.getMessage());
        }
        catch (Exception e) {
            alert("Erro", e.getMessage());
        }
    }


    public void criarUsuario(View view){
        try {
            Intent intent = new Intent(this, CadastrarUsuario.class);
            startActivity(intent);
        }
        catch (Exception e){
            alert("Erro", e.getMessage());
        }
    }
}