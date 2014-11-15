package br.com.ShoolDrive.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
@SequenceGenerator(name = "curso_seq", sequenceName = "curso_seq", allocationSize = 1, initialValue = 1)
public class Curso implements Serializable{


	private static final long serialVersionUID = 1916121892317121323L;

	public Curso() {}
	
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "curso_seq")
	private long id;

	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy="curso")
	private List<Disciplina> disciplina;
	
	@ManyToOne
	@JoinColumn(name="semestre_id")
	private Semestre semestre;
	
	
	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
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

	public List<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
}
