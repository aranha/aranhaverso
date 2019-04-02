package br.com.gastronomia.model;


import br.com.gastronomia.dto.IngredienteCadastroDTO;
import br.com.gastronomia.util.TipoDeIngrediente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe modelo para o acesso ao banco de dados.
 * 
 * @author Luis Santana - luis.arseno@acad.pucrs.br
 * @since 11/08/2017
 * 
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "Ingrediente")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = -789863172532826108L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdIngrediente")
	private long id;

	@NotEmpty
	@Column(name = "Nome", unique = true)
	private String nome;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "IdUsuario", nullable = false)
	private Usuario criador;

	@NotEmpty
	@Column(name = "Origem")
	private String origem;

	@NotEmpty
	@Column(name = "Alergenico")
	private String alergenico;

	@Column(name= "Status", nullable = false)
	private boolean status;

    //Relacionamento implementado -- lado forte
    @OneToMany(
    		mappedBy = "ingrediente",
			fetch = FetchType.EAGER,
			cascade = {
					CascadeType.ALL
			})
    @JsonManagedReference
	private Set<IngredienteAtributo> ingredienteAtributo = new HashSet<>();
	
	@Column(name = "Tipo")
    @Enumerated(EnumType.STRING)
	private TipoDeIngrediente tipo;

	public Ingrediente() {

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

	public Usuario getCriador() { return criador; }

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setAlergenico(String alergenico) {this.alergenico = alergenico;}

	public String getAlergenico() {return alergenico;}
	public Set<IngredienteAtributo> getIngredienteAtributo() {
		return ingredienteAtributo;
	}

	public void setIngredienteAtributo(Set<IngredienteAtributo> ingredienteAtributo) {
		this.ingredienteAtributo = ingredienteAtributo;
	}

	public void addIngredienteAtributo(Atributo atributo, String valor) {
		IngredienteAtributo ingredienteAtributo = new IngredienteAtributo();
		ingredienteAtributo.setValor(valor);
		ingredienteAtributo.setAtributo(atributo);
		ingredienteAtributo.setIngrediente(this);
		this.ingredienteAtributo.add(ingredienteAtributo);
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TipoDeIngrediente getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDeIngrediente tipo) {
			this.tipo= tipo;
	}

	public void setIngredienteInfoCadastro(IngredienteCadastroDTO infoCadastro, Usuario criador) {
		this.nome = infoCadastro.getNome();
		this.origem = infoCadastro.getOrigem();
		this.criador = criador;
		this.status = infoCadastro.getStatus();
		this.tipo = infoCadastro.getTipo();
	}

	
    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", criador=" + criador +
                ", origem='" + origem + '\'' +
                ", ingredienteAtributo=" + ingredienteAtributo +
                ", status=" + status +
                ", tipo=" + tipo.toString() +
                '}';
    }
}