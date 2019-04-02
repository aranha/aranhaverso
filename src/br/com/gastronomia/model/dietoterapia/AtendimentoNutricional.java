package br.com.gastronomia.model.dietoterapia;

import br.com.gastronomia.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AtendimentoNutricional")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtendimentoNutricional {
    @Id
    @GeneratedValue
    @Column(name = "IdAtendimento")
    private long id;

    @OneToOne
    @JoinColumn(name = "IdAluno")
    private Usuario aluno;

    @OneToOne
    @JoinColumn(name = "IdProfessor")
    private Usuario professor;

    @OneToOne
    @JoinColumn(name = "IdPaciente")
    private Paciente paciente;

    @Column(name = "data")
    private Date data;

    @Column(name = "status")
    private Boolean status;


    public AtendimentoNutricional() {
        // empty
    }

    public AtendimentoNutricional(Usuario aluno, Usuario professor, Paciente paciente, Date data, Boolean status) {
        this.aluno = aluno;
        this.professor = professor;
        this.paciente = paciente;
        this.data = data;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
