package br.com.gastronomia.model;

import java.io.Serializable;

public class ReceitaIngredienteId implements Serializable {

    protected Ingrediente ingrediente;
    protected Receita receita;

    public ReceitaIngredienteId() {
    }

    public ReceitaIngredienteId(Ingrediente ingrediente, Receita receita) {
        this.ingrediente = ingrediente;
        this.receita = receita;
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

    public void setAtributo(Receita receita) {
        this.receita = receita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceitaIngredienteId that = (ReceitaIngredienteId) o;

        if (ingrediente != null ? !ingrediente.equals(that.ingrediente) : that.ingrediente != null) return false;
        return receita != null ? receita.equals(that.receita) : that.receita == null;
    }

    @Override
    public int hashCode() {
        int result = ingrediente.hashCode();
        result = 31 * result + receita.hashCode();
        return result;
    }
}