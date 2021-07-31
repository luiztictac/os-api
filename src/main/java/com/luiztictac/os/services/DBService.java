package com.luiztictac.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiztictac.os.domain.Cliente;
import com.luiztictac.os.domain.OS;
import com.luiztictac.os.domain.Tecnico;
import com.luiztictac.os.domain.enums.Prioridade;
import com.luiztictac.os.domain.enums.Status;
import com.luiztictac.os.repositories.ClienteRepository;
import com.luiztictac.os.repositories.OSRepository;
import com.luiztictac.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Luiz Santana", "724.270.830-38", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Lidian Lima", "148.351.470-65", "(77) 97777-7777");
		Cliente c1 = new Cliente(null, "Betina Campos", "363.732.070-30", "(88) 98888-7777");
		Cliente c2 = new Cliente(null, "Carla Santos", "875.996.110-45", "(88) 86666-4321");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1,c2));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
