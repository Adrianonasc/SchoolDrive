package br.com.pacotePrincipal.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="usuario")
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq",allocationSize=1,initialValue=1)
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq")
	private long id;
	
	public Usuario() {
	}

	public Usuario(long id, String email, String senha, String nome, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.role = role;
	}

	@Column(name = "email", length = 150, nullable = false)
	private String email;
	
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
