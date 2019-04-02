package br.com.gastronomia.model;

import java.io.Serializable;

public class ReceitaImagemId implements Serializable {

    protected Imagem imagem;
    protected Receita receita;

    public ReceitaImagemId() {
    }

    public ReceitaImagemId(Imagem imagem, Receita receita) {
        this.imagem = imagem;
        this.receita = receita;
    }


    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }


    public Receita getReceita() {
        return receita;
    }

    public void setAtributo(Receita receita) {
        this.receita = receita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceitaImagemId that = (ReceitaImagemId) o;

        if (imagem != null ? !imagem.equals(that.imagem) : that.imagem != null) return false;
        return receita != null ? receita.equals(that.receita) : that.receita == null;
    }

    @Override
    public int hashCode() {
        int result = imagem.hashCode();
        result = 31 * result + receita.hashCode();
        return result;
    }
}