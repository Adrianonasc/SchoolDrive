package br.com.ShoolDrive.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	private Date dataEntrega;

	@Column
	private byte[] anexo;

	@Column(name = "nome_anexo")
	private String nomeAnexo;

	@Column
	private String nota;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "trabalho_id")
	private Trabalho trabalho;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataEntrega() {
		return this.dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public byte[] getAnexo() {
		return this.anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getNota() {
		return this.nota;
	}

	public String getNomeAnexo() {
		return this.nomeAnexo;
	}

	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Trabalho getTrabalho() {
		return this.trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}



}
