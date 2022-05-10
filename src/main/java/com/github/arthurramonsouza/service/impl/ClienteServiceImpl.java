package com.github.arthurramonsouza.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.arthurramonsouza.model.Cliente;
import com.github.arthurramonsouza.model.Endereco;
import com.github.arthurramonsouza.repository.ClienteRepository;
import com.github.arthurramonsouza.repository.EnderecoRepository;
import com.github.arthurramonsouza.service.ClienteService;
import com.github.arthurramonsouza.service.ViaCepService;

public class ClienteServiceImpl implements ClienteService {

	// Design pattern Singleton: implements Spring components with @Autowire
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ViaCepService service;
	
	// Design pattern Stategy: implements the methods defined in the interfaces
	// Design pattern Facade: abstract the integration with subsystems (ViaCEP website), providing a simple interface (like ViaCepService)

	@Override
	public Cliente buscarPorId(Long id) {
		// Find client by id
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent())
			return cliente.get();
		else
			return null;
	}

	@Override
	public Iterable<Cliente> buscarTodos() {
		// Find all clients
		return clienteRepository.findAll();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteDatabase = clienteRepository.findById(id);
		if (clienteDatabase.isPresent())
			salvarClienteComCep(cliente);
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	public void salvarClienteComCep(Cliente cliente) {
		// Verify if the client address exist (by CEP)
		String cepNovoCliente = cliente.getEndereco().getCep();
		Optional<Endereco> enderecoNovoCliente = enderecoRepository.findById(cepNovoCliente);
		Endereco endereco = null;
		if (enderecoNovoCliente.isPresent()) {
			// If exist
			endereco = enderecoNovoCliente.get();
		} else {
			// If not exist, go to ViaCEP API to get the new address and persist in database
			endereco = service.consultarCep(cepNovoCliente);
			enderecoRepository.save(endereco);
		}
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
}