package com.example.marcos.unaspwhatsapp_sqledition.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;


public class DBUsuario {

    private int id;
    private String login;
    private String senha;
    private String nome;
    public static int usuarioLogado;

    public String _mensagem;
    public boolean _status;

    public DBUsuario() {
        super();
        this.id = -1;
        this.nome = "";
        this.login = "";
        this.senha = "";
    }

    public ArrayList<DBUsuario> getLista() {
        DB db = new DB();
        ArrayList<DBUsuario> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM usuario");
            if (resultSet != null) {
                while (resultSet.next()) {

                    DBUsuario obj = new DBUsuario();

                    obj.setId(resultSet.getInt("id"));
                    obj.setNome(resultSet.getString("nome"));
                    obj.setLogin(resultSet.getString("login"));
                    obj.setSenha(resultSet.getString("senha"));
                    lista.add(obj);

                }
            }
        } catch (Exception ex) {
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    public void salvar(String teste) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, LoginException {
        String comando = "";
        if (this.getId() == -1) {
            comando = String.format("INSERT INTO usuario (nome,login,senha) VALUES ('%s','%s','%s');",
                    this.getNome(), this.getLogin(), this.getSenha());
        } else {
            comando = String.format("UPDATE usuario SET nome ='%s', login ='%s', senha = '%s', WHERE id = %d;",
                    this.getNome(), this.getLogin(), this.getSenha(), this.getId());
        }
        DB db = new DB();
        db.update(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String comando = String.format("DELETE FROM usuario WHERE id = %d;", this.getId());
        DB db = new DB();
        db.execute(comando);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


