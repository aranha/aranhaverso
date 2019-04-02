package br.com.gastronomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Arrays;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Imagem")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @NotEmpty
    @Column(name = "Ext")
    private String ext;

    @NotEmpty
    @Lob
    @Column(name = "Base64")
    private byte[] base64;

    /**
     * Construtor vazio.
     *
     **/

    public Imagem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public byte[] getBase64() {
        return base64;
    }

    public void setBase64(byte[] base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", ext='" + ext + '\'' +
                ", base64=" + Arrays.toString(base64) +
                '}';
    }
}