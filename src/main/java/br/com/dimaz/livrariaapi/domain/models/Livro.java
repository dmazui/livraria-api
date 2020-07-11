package br.com.dimaz.livrariaapi.domain.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livros", indexes = {@Index(name = "livro_index", columnList = "id", unique = true)})
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false )
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(length = 60)
	private String titulo;
	
	@Column(length = 13)
	private String isbn;
	
	@Column(length = 60)
	private String autor;
	
	private LocalDate lancamento;
	
	@Lob
	private String descricao;
		
}
