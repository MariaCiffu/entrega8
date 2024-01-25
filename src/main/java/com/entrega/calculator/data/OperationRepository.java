package com.entrega.calculator.data;

import com.entrega.calculator.model.pojo.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OperationRepository {

    private final OperationJpaRepository repository;

    public List<Operation> getOperations() {
        return repository.findAll();
    }

    public Operation getOperationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Operation save(Operation operation) {
        return repository.save(operation);
    }


}
