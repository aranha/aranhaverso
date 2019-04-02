package br.com.gastronomia.model.dietoterapia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Paciente")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPaciente")
    private long id;

    @Column(name = "Nome", unique = false, nullable = false)
    private String nome;

    @Column(name = "Sexo", unique = false, nullable = false)
    private Character sexo;

    @Column(name = "RestricaoAlimentar", unique = false, nullable = false)
    private String restricaoAlimentar;

    @Column(name = "DataNascimento", unique = false, nullable = false)
    private Date dataNascimento;

    @Column(name= "Status", nullable = false)
    private boolean status;

    public Paciente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(String restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }
}

