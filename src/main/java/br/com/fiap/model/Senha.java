package br.com.fiap.model;

public class Senha {
    private String desc;
    private String senha;

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Senha String" + this.desc + " " + this.senha;
    }
}
