package br.com.gastronomia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "ReceitaImagem")
@IdClass(ReceitaImagemId.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ReceitaImagem implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdImagem")
    private Imagem imagem;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdReceita")
    @JsonBackReference
    private Receita receita;

    public ReceitaImagem() {
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

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    @Override
    public String toString() {
        return "ReceitaImagem {" +
                "imagem='" + imagem +
                ", receita='" + receita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceitaImagem that = (ReceitaImagem) o;

        if (imagem != null ? !imagem.equals(that.imagem) : that.imagem != null) return false;
        return receita != null ? receita.equals(that.receita) : that.receita == null;
    }

    @Override
    public int hashCode() {
        int result = imagem != null ? imagem.hashCode() : 0;
        result = 31 * result + (receita != null ? receita.hashCode() : 0);
        return result;
    }
}
