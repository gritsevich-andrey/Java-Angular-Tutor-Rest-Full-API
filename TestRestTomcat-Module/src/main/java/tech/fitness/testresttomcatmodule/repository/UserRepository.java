package tech.fitness.testresttomcatmodule.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);

    List<User> findByDate(Date date);

    // пример кастомного запроса который возвращает Stream (Java 8 Stream API)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Stream<User> findByEmailReturnStream(@Param("email") String email);
}
