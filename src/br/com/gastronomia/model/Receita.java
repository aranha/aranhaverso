package br.com.gastronomia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

/**
 * Classe modelo para o acesso ao banco de dados.
 *
 * @author Bolivar Pereira - bolivar.pereira@acad.pucrs.br
 * @since 11/08/2017
 *
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Receita")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Receita implements Serializable {

    private static final long serialVersionUID = -789863172532826108L;

    private enum
    Tipo {
        PUBLICO, PRIVADO, NULL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReceita")
    private long id;

    @NotEmpty
    @Column(name = "Nome")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Nota", nullable = true, foreignKey=@ForeignKey(name = "FK_RECEITA_NOTA"))
    private Nota nota;

    @Column(name= "Publicada", nullable = false)
    private boolean publicada;

    @Column(name= "Status", nullable = false)
    private boolean status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="Passos", joinColumns = @JoinColumn(name = "IdReceita"))
    @Column(name = "Passos", nullable = false)
    private Set<String> passos = new HashSet<>();

    @Column(name = "Rendimento", nullable = false)
    private String rendimento;

    @Column(name = "Tempo", nullable = false)
    private String tempo;

    @Column(name = "Peso", nullable = false)
    private double peso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Imagem", nullable = false, foreignKey=@ForeignKey(name = "FK_RECEITA_IMAGEM"))
    private Imagem imagem;

    @Column(name = "Dificuldade", nullable = true)
    @Max(5)
    private int dificuldade;

    @Column(name = "Tipo", nullable = false)
    private Tipo tipo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ReceitaUsuarios",
            joinColumns=
                    {@JoinColumn(name="IdReceita"), }, inverseJoinColumns=
            {@JoinColumn(name="IdUsuario")})
    private Set<Usuario> criadores = new HashSet<>();

    @OneToMany(
            mappedBy = "receita",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JsonManagedReference
    private Set<ReceitaIngrediente> receitaIngrediente = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IdProfessor", nullable = false, foreignKey=@ForeignKey(name = "FK_RECEITA_PROFESSOR"))
    private Usuario professor;

    @Column(name = "Data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GrupoReceita", nullable = false, foreignKey=@ForeignKey(name = "FK_RECEITA_GRUPORECEITA"))
    private GrupoReceitas grupoReceita;

    public Receita() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }

    public boolean getPublicada() {
        return publicada;
    }

    public void setPublicada(boolean publicada) {
        this.publicada = publicada;
    }

    public int getDificuldade() { return dificuldade; }

    public void setDificuldade(int dificuldade) { this.dificuldade = dificuldade; }

    public Set<String> getPassos() { return passos; }

    public void setPassos(Set<String> passos) {
        this.passos = passos;
    }

    public String getRendimento() {
        return rendimento;
    }

    public void setRendimento(String rendimento) {
        this.rendimento = rendimento;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Set<Usuario> getCriadores() {
        return criadores;
    }

    public void setCriadores(Set<Usuario> criadores) {
        this.criadores = criadores;
    }

    public Set<ReceitaIngrediente> getReceitaIngrediente() {
        return receitaIngrediente;
    }

    public void setReceitaIngrediente(Set<ReceitaIngrediente> receitaIngrediente) {
        this.receitaIngrediente = receitaIngrediente;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public GrupoReceitas getGrupoReceita() {
        return grupoReceita;
    }

    public void setGrupoReceita(GrupoReceitas grupoReceita) {
        this.grupoReceita = grupoReceita;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public boolean isPublicada() {
        return publicada;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", nome='" + nome +
                ", passos=" + passos.toString() +
                ", rendimento='" + rendimento  +
                ", tempo='" + tempo  +
                ", imagem='" + imagem  +
                ", tipo='" + tipo  +
                ", ingredienteReceita=" + receitaIngrediente +
                ", criadores=" + criadores +
                ", professor=" + professor +
                ", publicada=" + publicada +
                '}';
    }
}
