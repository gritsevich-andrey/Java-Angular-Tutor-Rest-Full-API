package tech.fitness.testresttomcatmodule.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fitness.testresttomcatmodule.models.Client;
import tech.fitness.testresttomcatmodule.services.ClientService;

@RestController
@AllArgsConstructor
public class ClientsController {
    private final ClientService clientService;

    @GetMapping("/clients")
    public Iterable<Client> findAllClients() {
        return clientService.readAll();
    }
}
