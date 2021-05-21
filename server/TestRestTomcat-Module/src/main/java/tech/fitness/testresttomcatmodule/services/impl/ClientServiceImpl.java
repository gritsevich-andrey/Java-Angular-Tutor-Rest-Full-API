package tech.fitness.testresttomcatmodule.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.fitness.testresttomcatmodule.models.Client;
import tech.fitness.testresttomcatmodule.repository.ClientRepository;
import tech.fitness.testresttomcatmodule.services.ClientService;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public
    Iterable<Client> readAll() {
        return clientRepository.findAll();
    }
}
