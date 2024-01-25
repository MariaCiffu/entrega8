package com.entrega.calculator.service;

import com.entrega.calculator.data.OperationRepository;
import com.entrega.calculator.model.pojo.Operation;
import com.entrega.calculator.model.request.CreateOperationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class OperationsServiceImpl implements OperationsService {

	@Autowired
	private OperationRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Operation> getOperations() {

		List<Operation> operations = repository.getOperations();
		return operations.isEmpty() ? null : operations;
	}

	@Override
	public Operation getOperationById(String productId) {
		return repository.getOperationById(Long.valueOf(productId));
	}


	@Override
	public Operation addOperation(CreateOperationRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null && StringUtils.hasLength(request.getTipoOperacion().trim())
				&& !request.getElementos().isEmpty()) {
			Double resultado = null;
			switch (request.getTipoOperacion().toLowerCase()){
				case "sumar":
					resultado = request.getElementos().parallelStream().mapToDouble(e -> e).sum();
					break;
				case "restar":
					resultado = request.getElementos().stream().reduce(0.0, (a, b) -> a - b);
					break;
				case "multiplicar":
					resultado = request.getElementos().get(0)*request.getElementos().get(1);
					break;
				case "dividir":
					resultado = request.getElementos().get(0)/request.getElementos().get(1);
					break;
				case "raiz":
					resultado = Math.pow(request.getElementos().get(0),1/request.getElementos().get(1));
					break;
				case "potencia":
					resultado = Math.pow(request.getElementos().get(0),request.getElementos().get(1));
					break;
				default:
            }

			Operation operation = Operation.builder().tipoOperacion(request.getTipoOperacion()).elementos(request.getElementos())
					.resultado(resultado).build();

			return repository.save(operation);
		} else {
			return null;
		}
	}
}
