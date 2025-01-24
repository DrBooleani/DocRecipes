package com.drbooleani.docrecipesapi.domain.team;

import com.drbooleani.docrecipesapi.domain.operation.OperationProvider;
import com.drbooleani.docrecipesapi.domain.operation.OperationProviderRepository;
import com.drbooleani.docrecipesapi.domain.operation.dto.OperationProviderDTO;
import com.drbooleani.docrecipesapi.domain.team.dto.TeamMemberDTO;
import com.drbooleani.docrecipesapi.enums.OperationProviderType;
import com.drbooleani.docrecipesapi.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final OperationProviderRepository operationProviderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamMemberService(TeamMemberRepository teamMemberRepository, OperationProviderRepository operationProviderRepository, ModelMapper modelMapper) {
        this.teamMemberRepository = teamMemberRepository;
        this.operationProviderRepository = operationProviderRepository;
        this.modelMapper = modelMapper;
    }

    public List<TeamMemberDTO> getAllTeamMembers() {
        List<TeamMemberDTO> teamMemberDTOList = new ArrayList<>();
        teamMemberRepository.findAll().forEach(teamMember -> {
            TeamMemberDTO teamMemberDto = modelMapper.map(teamMember, TeamMemberDTO.class);
            teamMemberDto.setOperationProviderDTO(modelMapper.map(teamMember.getOperationProvider(), OperationProviderDTO.class));
            teamMemberDTOList.add(teamMemberDto);
        });
        return teamMemberDTOList;
    }


    public TeamMemberDTO findTeamMemberById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team Member not found with ID: " + id));
        var response = modelMapper.map(teamMember, TeamMemberDTO.class);
        response.setOperationProviderDTO(modelMapper.map(teamMember.getOperationProvider(), OperationProviderDTO.class));
        return response;
    }

    public TeamMemberDTO createTeamMember(TeamMemberDTO teamMemberDTO) {
        OperationProvider operationProvider = operationProviderRepository.findByType(OperationProviderType.valueOf(teamMemberDTO.getOperationProviderDTO().getType()))
                .orElseGet(() -> {
                    OperationProvider newOperationProvider = modelMapper.map(teamMemberDTO.getOperationProviderDTO(), OperationProvider.class);
                    return operationProviderRepository.save(newOperationProvider);
                });

        TeamMember teamMember = modelMapper.map(teamMemberDTO, TeamMember.class);
        teamMember.setOperationProvider(operationProvider);
        teamMember = teamMemberRepository.save(teamMember);

        var response = modelMapper.map(teamMember, TeamMemberDTO.class);
        response.setOperationProviderDTO(modelMapper.map(operationProvider, OperationProviderDTO.class));
        return response;
    }

    public TeamMemberDTO updateTeamMember(Long id, TeamMemberDTO teamMemberDTO) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team Member not found with ID: " + id));

        teamMember.setName(teamMemberDTO.getName());

        OperationProvider operationProvider = modelMapper.map(teamMemberDTO.getOperationProviderDTO(), OperationProvider.class);
        teamMember.setOperationProvider(operationProvider);

        teamMemberRepository.save(teamMember);
        var response = modelMapper.map(teamMember, TeamMemberDTO.class);
        response.setOperationProviderDTO(modelMapper.map(operationProvider, OperationProviderDTO.class));
        return response;
    }

    public void deleteTeamMember(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team Member not found with ID: " + id));
        teamMemberRepository.delete(teamMember);
    }

}
