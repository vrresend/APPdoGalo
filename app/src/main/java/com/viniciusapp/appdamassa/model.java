package com.viniciusapp.appdamassa;

public class model {

    String imagem, id, nome, titulo;

    public model() {

    }


    public model(String imagem, String id, String nome, String titulo) {
        this.imagem = imagem;
        this.id = id;
        this.nome = nome;
        this.titulo = titulo;


    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}