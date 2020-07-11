package br.com.dimaz.livrariaapi.domain.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.dimaz.livrariaapi.domain.dtos.LivroDTO;
import br.com.dimaz.livrariaapi.domain.models.Livro;

@Component
public class LivroToDto implements Converter<Livro, LivroDTO>{

	@Override
	public LivroDTO convert(Livro livro) {
		LivroDTO dto = new LivroDTO();
		
		dto.setId(livro.getId());
		dto.setTitulo(livro.getTitulo());
		dto.setIsbn(livro.getIsbn());
		dto.setAutor(livro.getAutor());
		dto.setLancamento(livro.getLancamento());
		dto.setDescricao(livro.getDescricao());
		
		return dto;
	}
	

}
