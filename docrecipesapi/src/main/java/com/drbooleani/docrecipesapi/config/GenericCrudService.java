package com.drbooleani.docrecipesapi.config;

import com.drbooleani.docrecipesapi.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenericCrudService<Entity, DTO> {

    private final CrudRepository<Entity, Long> repository;
    private final ModelMapper modelMapper;

    @Autowired
    public GenericCrudService(CrudRepository<Entity, Long> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<DTO> getAll(Class<DTO> dtoClass) {
        return ((List<Entity>) this.repository.findAll())
                .stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public DTO getById(Long id, Class<Entity> entityClass, Class<DTO> dtoClass) {
        return this.modelMapper.map(this.getEntity(id, entityClass), dtoClass);
    }

    @Transactional
    public DTO save(DTO dto, Class<Entity> entityClass, Class<DTO> dtoClass) {
        Entity entity = modelMapper.map(dto, entityClass);
        return modelMapper.map(repository.save(entity), dtoClass);
    }

    @Transactional
    public DTO update(Long id, DTO dto, Class<Entity> entityClass, Class<DTO> dtoClass) {
        Entity entity = getEntity(id, entityClass);
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
    public void delete(Long id, Class<Entity> entityClass) {
        Entity entity = getEntity(id, entityClass);
        repository.delete(entity);
    }

    private Entity getEntity(Long id, Class<Entity> entityClass) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityClass.getSimpleName() + " Not Found with ID " + id));
    }
}
