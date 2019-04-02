package br.com.gastronomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Nota")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Nota", nullable = false)
    private float nota;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IdAvaliador", nullable = false, foreignKey=@ForeignKey(name = "FK_NOTA_AVALIADOR"))
    private Usuario avaliador;

    /**
     * Construtor vazio.
     *
     **/

    public Nota() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", nota='" + nota + '\'' +
                ", avaliador=" + avaliador +
                '}';
    }
}