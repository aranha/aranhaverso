package br.com.gastronomia.dto;

public class IngredienteAtributoDto {

    private int idIngrediente;
    private int idAtributo;
    private String Valor;

    public IngredienteAtributoDto() { };

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public int getIdAtributo() {
        return idAtributo;
    }

    public String getValor() {
        return Valor;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setIdAtributo(int idAtributo) {
        this.idAtributo = idAtributo;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}
