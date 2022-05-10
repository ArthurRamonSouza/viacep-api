package com.github.arthurramonsouza.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.arthurramonsouza.model.Endereco;

/**
 * Client HTTP, created via <b>OpenFeing</b>, for consuming <b>ViaCEP</b> API.
 * Desing pattern Facade.
 *  
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign>Spring Cloudy OpenFeing</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * @author ArthurRamonSouza
 *
 */

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

	@RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
	//@GetMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String cep);
	
}