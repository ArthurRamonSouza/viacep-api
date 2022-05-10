package com.github.arthurramonsouza.service;

import org.springframework.stereotype.Service;

import com.github.arthurramonsouza.model.Cliente;

/**
 *  This interface apply the design pattern <b>Strategy</b> in the Cliente domain.
 *  So, if necessary, we can have multiple implementations of the Cliente interface.
 * 
 * @author ArthurRamonSouza
 *
 */

@Service
public interface ClienteService {
	
		Iterable<Cliente> buscarTodos();
		
		Cliente buscarPorId(Long id);
		
		void inserir(Cliente cliente);
		
		void atualizar(Long id, Cliente cliete);
		
		void deletar(Long id);
}