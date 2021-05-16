package tech.fitness.testresttomcatmodule.repository;

import org.springframework.data.repository.CrudRepository;
import tech.fitness.testresttomcatmodule.models.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
