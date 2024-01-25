package com.entrega.calculator.service;



import com.entrega.calculator.model.pojo.Operation;
import com.entrega.calculator.model.request.CreateOperationRequest;

import java.util.List;

public interface OperationsService {
	
	List<Operation> getOperations();
	Operation getOperationById(String productId);
	Operation addOperation(CreateOperationRequest request);

}
