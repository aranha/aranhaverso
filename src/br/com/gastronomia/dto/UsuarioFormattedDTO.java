package br.com.gastronomia.dto;

import br.com.gastronomia.model.Usuario;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UsuarioFormattedDTO implements Serializable {
	private static final long serialVersionUID = -789863172532826108L;
	private String cpf;
	private String email;
	private String name;
	private String phone;
	private String role;
	private boolean active;

	public UsuarioFormattedDTO() {}

	public String getCpf() {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isValid() {
		return (this.cpf != null);
	}

	public static UsuarioFormattedDTO getFromUser(Usuario usuario) {
		UsuarioFormattedDTO dto = new UsuarioFormattedDTO();

		dto.setCpf(usuario.getCpf());
		dto.setEmail(usuario.getEmail());
		dto.setName(usuario.getNome());
		dto.setRole(usuario.getTipo().toString());
		dto.setActive(usuario.isStatus());

		return dto;
	}

	public static List<UsuarioFormattedDTO> getFromUser(List<Usuario> usuario) {
		List<UsuarioFormattedDTO> list = new LinkedList<>();

		for (Usuario u : usuario)
			list.add(getFromUser(u));

		return list;
	}
}