package com.fitness.instructorservice.services.impl;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.repository.ProgramRepository;
import com.fitness.instructorservice.services.ProgramService;
import com.fitness.instructorservice.web.payload.ProgramRequest;
import com.fitness.instructorservice.web.payload.ProgramResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public Mono<ProgramResponse> createProgram(ProgramRequest request) {
        String name = request.getName();
        String imageSrc = request.getImageSrc();
        String instructor = request.getInstructor();
        double cost = request.getCost();
        List<String> categories = request.getCategory();
        Program program = new Program(null, name, imageSrc, instructor, cost, categories);
        return programRepository.save(program)
                .flatMap(programResponse -> {
                    ProgramResponse programResponse1 = new ProgramResponse(programResponse.getId());
                    return Mono.just(programResponse1);
                });

    }

    @Override
    public Mono<Program> findById(String id) {
        return programRepository.findById(id);
    }
//    public Flux<UserBO> getAllUsers(){
//        return userRepository.findAll().flatMap(UserService::covertUserDAOToBUserBO);
//    }
//public Mono<UserBO> saveUser(UserBO userBO)
//{
//    return
//            userRepository.save(covertUserBOToBUserDAO(userBO)).flatMap(UserService::covertUserDAOToBUserBO);
//}

    @Override
    public Flux<Program> findAll() {
        return programRepository.findAll();

    }

    @Override
    public Mono<Void> deleteById(String id) {
        return programRepository.deleteById(id);
    }

    @Override
    public Flux<ProgramResponse> findByCategory(String category) {
        return programRepository.findByCategory(category);
    }
}
