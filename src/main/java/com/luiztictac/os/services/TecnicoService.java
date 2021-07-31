package com.luiztictac.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiztictac.os.domain.Pessoa;
import com.luiztictac.os.domain.Tecnico;
import com.luiztictac.os.dtos.TecnicoDTO;
import com.luiztictac.os.repositories.PessoaRepository;
import com.luiztictac.os.repositories.TecnicoRepository;
import com.luiztictac.os.resources.exceptions.DataIntegratyViolationException;
import com.luiztictac.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * Busca Tecnico pelo ID
	 */
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	/*
	 * Busca todos os Tecnico da base de dados
	 */
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	/*
	 *Cria um Tecnico 
	 */
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
				
		// ou pode ser feito igual abaixo		
		//Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		//return repository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}
	
	/*
	 * Deleta um Tecnico pelo ID 
	 */
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser excluído!");
		}
		repository.deleteById(id);
	}
	
	/*
	 * Busca Tecnico pelo CPF 
	 */
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		
		if(obj != null) {
			return obj;
		}
		return null;
	}

	
	
}
