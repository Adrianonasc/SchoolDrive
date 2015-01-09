package br.com.ShoolDrive.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
@SequenceGenerator(name = "disciplina_seq", sequenceName = "disciplina_seq", allocationSize = 1, initialValue = 1)
public class Disciplina implements Serializable {
	private static final long serialVersionUID = -8641831706678922166L;

	public Disciplina() {}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "disciplina_seq")
	private long id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@ManyToMany(mappedBy = "disciplinas", fetch = FetchType.LAZY)
	private List<Aluno> alunos;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "disciplina_id")
	private List<Trabalho> trabalhos;

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Trabalho> getTrabalhos() {
		return this.trabalhos;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

}
