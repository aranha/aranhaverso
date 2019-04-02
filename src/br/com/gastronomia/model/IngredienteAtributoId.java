package br.com.gastronomia.model;

import java.io.Serializable;

public class IngredienteAtributoId implements Serializable {

    protected Ingrediente ingrediente;
    protected Atributo atributo;

    public IngredienteAtributoId() {
    }

    public IngredienteAtributoId(Ingrediente ingrediente, Atributo atributo) {
        this.ingrediente = ingrediente;
        this.atributo = atributo;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredienteAtributoId)) return false;

        IngredienteAtributoId that = (IngredienteAtributoId) o;

        if (!ingrediente.equals(that.ingrediente)) return false;
        return atributo.equals(that.atributo);
    }

    @Override
    public int hashCode() {
        int result = ingrediente.hashCode();
        result = 31 * result + atributo.hashCode();
        return result;
    }
}