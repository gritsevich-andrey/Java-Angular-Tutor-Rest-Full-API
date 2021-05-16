package tech.fitness.testresttomcatmodule.services;

import tech.fitness.testresttomcatmodule.models.Client;

public interface ClientService {

    Iterable<Client> readAll();
}
