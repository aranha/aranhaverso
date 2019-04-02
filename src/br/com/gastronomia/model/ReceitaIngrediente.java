package br.com.gastronomia.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe modelo para o acesso ao banco de dados.
 *
 * @author Bolivar Pereira - bolivar.pereira@acad.pucrs.br
 * @since 11/08/2017
 *
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "ReceitaIngrediente")
@IdClass(ReceitaIngredienteId.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ReceitaIngrediente implements Serializable {

    private static final long serialVersionUID = -78917652532826108L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdIngrediente")
    private Ingrediente ingrediente;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdReceita")
    @JsonBackReference
    private Receita receita;

    @Column(name = "CustoKg")
    private double custoKg;

    @Column(name = "FatorCorrecao")
    private double fatorCorrecao;

    @Column(name = "PesoG")
    private double pesoG;

    public ReceitaIngrediente() {
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public double getFatorCorrecao() {
        return fatorCorrecao;
    }

    public void setFatorCorrecao(double fatorCorrecao) {
        this.fatorCorrecao = fatorCorrecao;
    }

    public double getCustoKg() {
        return custoKg;
    }

    public void setCustoKg(double custoKg) {
        this.custoKg = custoKg;
    }

    public double getPesoG() {
        return pesoG;
    }

    public void setPesoG(double pesoG) {
        this.pesoG = pesoG;
    }

    @Override
    public String toString() {
        return "IngredienteAtributo{" +
                "ingrediente=" + ingrediente +
                ", custoKg='" + custoKg +
                ", pesoG='" + pesoG + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceitaIngrediente that = (ReceitaIngrediente) o;

        if (ingrediente != null ? !ingrediente.equals(that.ingrediente) : that.ingrediente != null) return false;
        return receita != null ? receita.equals(that.receita) : that.receita == null;
    }

    @Override
    public int hashCode() {
        int result = ingrediente != null ? ingrediente.hashCode() : 0;
        result = 31 * result + (receita != null ? receita.hashCode() : 0);
        return result;
    }
}
