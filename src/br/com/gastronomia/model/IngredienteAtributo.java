package br.com.gastronomia.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe modelo para o acesso ao banco de dados.
 *
 * @author Virgilius Santos - virgilius.santos@acad.pucrs.br
 * @since 11/08/2017
 *
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Ingrediente_Atributo")
@IdClass(IngredienteAtributoId.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredienteAtributo implements Serializable {

    private static final long serialVersionUID = -78917652532826108L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdIngrediente")
    @JsonBackReference
    private Ingrediente ingrediente;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdAtributo")
    private Atributo atributo;

    @Column(name = "Valor")
    private String valor;


    public IngredienteAtributo() {
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    @Override
    public String toString() {
        return "IngredienteAtributo{" +
                "atributo=" + atributo +
                ", valor='" + valor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredienteAtributo)) return false;

        IngredienteAtributo that = (IngredienteAtributo) o;

        if (ingrediente != null ? !ingrediente.equals(that.ingrediente) : that.ingrediente != null) return false;
        return atributo != null ? atributo.equals(that.atributo) : that.atributo == null;
    }

    @Override
    public int hashCode() {
        int result = ingrediente != null ? ingrediente.hashCode() : 0;
        result = 31 * result + (atributo != null ? atributo.hashCode() : 0);
        return result;
    }
}
