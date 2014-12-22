package br.com.ShoolDrive.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 20/12/2014
 * @author Adriano 
 * Classe Responsavel por Entrega de Trabalho
 */
@Entity
@Table(name = "entrega")
@SequenceGenerator(name = "entrega_seq", sequenceName = "entrega_seq", allocationSize = 1, initialValue = 1)
public class Entrega implements Serializable {

	private static final long serialVersionUID = 1L;

	public Entrega() {}
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entrega_seq")
	private long id;
	
	@Column(name="data_entrega")
	private String dataEntrega;
	
	@Column
	private byte[] anexo;
	
	@Column
	private String nota;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="trabalho_id")
	private Trabalho trabalho;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}
	
	
	
	
	
}
