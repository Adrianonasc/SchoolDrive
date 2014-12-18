package br.com.ShoolDrive.entidade;
/**
 * Classe tem a funcionalidade de ser a intancia para outras Entidade do tipo Usuario para 
 *  view na parte de login e cadastro de usuario
 * @author Adriano
 *
 */
public class Usuario{
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
	
	private String email;
	

	private String senha;

	private String nome;
	

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
