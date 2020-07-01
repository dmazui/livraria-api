package br.com.dimaz.livrariaapi.domain.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.dimaz.livrariaapi.domain.dtos.LivroDTO;
import br.com.dimaz.livrariaapi.domain.models.Livro;

@Component
public class DtoToLivro implements Converter<LivroDTO, Livro>{

	@Override
	public Livro convert(LivroDTO dto) {
		Livro livro = new Livro();
		livro.setId(dto.getId());
		livro.setTitulo(dto.getTitulo());
		livro.setIsbn(dto.getIsbn());
		livro.setAutor(dto.getAutor());
		
		return livro;
	}
	
	

}
