package com.entrega.calculator.data;

import com.entrega.calculator.model.pojo.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface OperationJpaRepository extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {

}
