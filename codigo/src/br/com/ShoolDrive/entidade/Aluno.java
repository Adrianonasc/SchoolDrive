package br.com.ShoolDrive.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
@SequenceGenerator(name = "aluno_seq", sequenceName = "aluno_seq", allocationSize = 1, initialValue = 1)
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "aluno_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "aluno_seq")
	private long id;

	public Aluno() {}
	public Aluno(long id, String email, String senha, String nome, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.role = role;
	}



	@Column(name = "email", length = 150)
	private String email;

	@Column(name = "senha", length = 100)
	private String senha;

	@Column(name = "nome", length = 100)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="registrado_disciplina",
			joinColumns={@JoinColumn(name="aluno_id")},
			inverseJoinColumns={@JoinColumn(name="disciplina_id")})
	private List<Disciplina> disciplinas; 


	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
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
