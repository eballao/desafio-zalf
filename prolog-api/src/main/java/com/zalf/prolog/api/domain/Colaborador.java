package com.zalf.prolog.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="colaborador")
public class Colaborador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cpf;
	
	private String nome;

}
