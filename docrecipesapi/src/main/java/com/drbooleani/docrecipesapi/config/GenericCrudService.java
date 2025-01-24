package com.drbooleani.docrecipesapi.config;

import com.drbooleani.docrecipesapi.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenericCrudService<Entity, DTO> {

    private final ModelMapper modelMapper;

    // Repositories injected via @Qualifier
    private final Map<String, CrudRepository<Entity, Long>> repositories;

    @Autowired
    public GenericCrudService(Map<String, CrudRepository<Entity, Long>> repositories, ModelMapper modelMapper) {
        this.repositories = repositories;
        this.modelMapper = modelMapper;
    }

    // This method will set the repository dynamically based on a string (repository name)
    private CrudRepository<Entity, Long> getRepository(String repositoryName) {
        CrudRepository<Entity, Long> repository = repositories.get(repositoryName);
        if (repository == null) {
            throw new IllegalArgumentException("No repository found for name: " + repositoryName);
        }
        return repository;
    }

    public List<DTO> getAll(Class<DTO> dtoClass, String repositoryName) {
        CrudRepository<Entity, Long> repository = getRepository(repositoryName);
        return ((List<Entity>) repository.findAll())
                .stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public DTO getById(Long id, Class<Entity> entityClass, Class<DTO> dtoClass, String repositoryName) {
        CrudRepository<Entity, Long> repository = getRepository(repositoryName);
        return modelMapper.map(this.getEntity(id, repository), dtoClass);
    }

    @Transactional
    public DTO save(DTO dto, Class<Entity> entityClass, Class<DTO> dtoClass, String repositoryName) {
        CrudRepository<Entity, Long> repository = getRepository(repositoryName);
        Entity entity = modelMapper.map(dto, entityClass);
        return modelMapper.map(repository.save(entity), dtoClass);
    }

    @Transactional
    public DTO update(Long id, DTO dto, Class<Entity> entityClass, Class<DTO> dtoClass, String repositoryName) {
        CrudRepository<Entity, Long> repository = getRepository(repositoryName);
        Entity entity = getEntity(id, repository);
        for (var entityField : entity.getClass().getDeclaredFields()) {
            try {
                entityField.setAccessible(true);
                String fieldName = entityField.getName();

                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }

                var dtoField = dto.getClass().getDeclaredField(fieldName);
                dtoField.setAccessible(true);

                Object dtoValue = dtoField.get(dto);

                if (dtoValue != null) {
                    entityField.set(entity, dtoValue);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.err.println(e.getMessage());
            }
        }
        return modelMapper.map(repository.save(entity), dtoClass);
    }

    @Transactional
    public void delete(Long id, Class<Entity> entityClass, String repositoryName) {
        CrudRepository<Entity, Long> repository = getRepository(repositoryName);
        Entity entity = getEntity(id, repository);
        repository.delete(entity);
    }

    private Entity getEntity(Long id, CrudRepository<Entity, Long> repository) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with ID " + id));
    }
}
