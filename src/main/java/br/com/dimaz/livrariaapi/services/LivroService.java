package br.com.dimaz.livrariaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimaz.livrariaapi.domain.dtos.LivroDTO;
import br.com.dimaz.livrariaapi.domain.mappers.DtoToLivro;
import br.com.dimaz.livrariaapi.domain.mappers.LivroToDto;
import br.com.dimaz.livrariaapi.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository repository;
	
	@Autowired
	DtoToLivro toLivro;

	@Autowired
	LivroToDto toDto;

	public LivroDTO save(LivroDTO livroDto) {
		return toDto.convert(repository.save(toLivro.convert(livroDto)));
	}

	public LivroDTO findById(Long id) {
		return toDto.convert(repository.findById(id).orElse(null));
	}

	public List<LivroDTO> findAll(LivroDTO filter) {
		//implementar select
		return null;
	}

	public LivroDTO delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public LivroDTO patch(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
