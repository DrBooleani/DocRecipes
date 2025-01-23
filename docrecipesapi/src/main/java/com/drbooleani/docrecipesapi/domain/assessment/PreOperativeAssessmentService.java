package com.drbooleani.docrecipesapi.domain.assessment;

import com.drbooleani.docrecipesapi.domain.assessment.dto.PreOperativeAssessmentDTO;
import com.drbooleani.docrecipesapi.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreOperativeAssessmentService {

    private final PreOperativeAssessmentRepository preOperativeAssessmentRepository;

    private final ModelMapper modelMapper;

    public PreOperativeAssessmentService(PreOperativeAssessmentRepository preOperativeAssessmentRepository, ModelMapper modelMapper) {
        this.preOperativeAssessmentRepository = preOperativeAssessmentRepository;
        this.modelMapper = modelMapper;
    }

    public PreOperativeAssessmentDTO getPreOperativeAssessment(String name) {
        PreOperativeAssessment preOperativeAssessment = preOperativeAssessmentRepository.findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pre-operative assessment not found with name: " + name));
        return modelMapper.map(preOperativeAssessment, PreOperativeAssessmentDTO.class);
    }

    public List<PreOperativeAssessmentDTO> getAllPreOperativeAssessments() {
        List<PreOperativeAssessmentDTO> list = new ArrayList<>();
        Iterable<PreOperativeAssessment> allPreOperativeAssessments = preOperativeAssessmentRepository.findAll();
        allPreOperativeAssessments.forEach(preOperativeAssessment ->
                list.add(modelMapper.map(preOperativeAssessment, PreOperativeAssessmentDTO.class))
        );
        return list;
    }

    public PreOperativeAssessmentDTO createPreOperativeAssessment(PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        PreOperativeAssessment preOperativeAssessment = modelMapper.map(preOperativeAssessmentDTO, PreOperativeAssessment.class);
        PreOperativeAssessment savedAssessment = preOperativeAssessmentRepository.save(preOperativeAssessment);
        return modelMapper.map(savedAssessment, PreOperativeAssessmentDTO.class);
    }

    public PreOperativeAssessmentDTO updatePreOperativeAssessment(String name, PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        PreOperativeAssessment existingAssessment = preOperativeAssessmentRepository.findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pre-operative assessment not found with name: " + name));

        existingAssessment.setName(preOperativeAssessmentDTO.getName());
        PreOperativeAssessment updatedAssessment = preOperativeAssessmentRepository.save(existingAssessment);
        return modelMapper.map(updatedAssessment, PreOperativeAssessmentDTO.class);
    }

    public void deletePreOperativeAssessment(String name) {
        PreOperativeAssessment assessment = preOperativeAssessmentRepository.findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pre-operative assessment not found with name: " + name));
        preOperativeAssessmentRepository.delete(assessment);
    }
}
