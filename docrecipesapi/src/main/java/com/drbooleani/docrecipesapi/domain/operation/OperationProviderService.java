package com.drbooleani.docrecipesapi.domain.operation;

import com.drbooleani.docrecipesapi.domain.operation.dto.OperationProviderDTO;
import com.drbooleani.docrecipesapi.enums.OperationProviderType;
import com.drbooleani.docrecipesapi.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationProviderService {

    private final OperationProviderRepository operationProviderRepository;
    private final ModelMapper modelMapper;

    public OperationProviderService(OperationProviderRepository operationProviderRepository, ModelMapper modelMapper) {
        this.operationProviderRepository = operationProviderRepository;
        this.modelMapper = modelMapper;
    }

    public OperationProviderDTO getOperationProviderById(String type) {
        OperationProvider operationProvider = operationProviderRepository.findByType(OperationProviderType.valueOf(type))
                .orElseThrow(() -> new ResourceNotFoundException("Operation Provider whit type " + type + " was not found"));
        return modelMapper.map(operationProvider, OperationProviderDTO.class);
    }


    public List<OperationProviderDTO> getAllOperationProviders() {
        List<OperationProviderDTO> operationProviderDTOList = new ArrayList<>();
        operationProviderRepository.findAll().forEach(operationProvider ->
                operationProviderDTOList.add(modelMapper.map(operationProvider, OperationProviderDTO.class))
        );
        return operationProviderDTOList;
    }

    public OperationProviderDTO createOperationProvider(OperationProviderDTO operationProviderDTO) {
        OperationProvider operationProvider = modelMapper.map(operationProviderDTO, OperationProvider.class);
        operationProvider = operationProviderRepository.save(operationProvider);
        return modelMapper.map(operationProvider, OperationProviderDTO.class);
    }

    public OperationProviderDTO updateOperationProvider(String id, OperationProviderDTO operationProviderDTO) {
        OperationProvider existingProvider = operationProviderRepository.findByType(OperationProviderType.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Operation Provider not found for type: " + id));

        existingProvider.setType(OperationProviderType.valueOf(operationProviderDTO.getType()));
        OperationProvider updatedProvider = operationProviderRepository.save(existingProvider);
        return modelMapper.map(updatedProvider, OperationProviderDTO.class);
    }



    public boolean deleteOperationProvider(String id) {
        return operationProviderRepository.findByType(OperationProviderType.valueOf(id)).map(operationProvider -> {
            operationProviderRepository.deleteById(OperationProviderType.valueOf(id));
            return true;
        }).orElse(false);
    }

}