package br.com.ShoolDrive.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "semestre")
@SequenceGenerator(name = "semestre_seq", sequenceName = "semestre_seq", allocationSize = 1, initialValue = 1)
public class Semestre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8547206754575134293L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "semestre_seq")
	private long id;

	@Column(name = "anoSemestre", length = 150)
	private String anoSemestre;
	
	/**
	 *Refere ao estado atual do semestre<p> 
	 *<i>TRUE </i><b> = Estado Aberto</b> <p>
	 *<i>FALSE </i><b>= Estado Fechado</b> 
	 */
	@Column(name = "status")
	private boolean status;


	@OneToMany(mappedBy="semestre",fetch=FetchType.LAZY)
	private List<Curso> cursos;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnoSemestre() {
		return anoSemestre;
	}

	public void setAnoSemestre(String anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
