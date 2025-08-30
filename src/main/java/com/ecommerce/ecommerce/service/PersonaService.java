package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.PersonaRequestDTO;
import com.ecommerce.ecommerce.dto.PersonaResponseDTO;
import com.ecommerce.ecommerce.exception.DuplicateResourceException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.mapper.PersonaMapper;
import com.ecommerce.ecommerce.models.Persona;
import com.ecommerce.ecommerce.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Inyecta PersonaRepository y PersonaMapper automáticamente
public class PersonaService {

   private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper; // ¡Inyectar el Mapper!

    // --- Métodos CRUD (ahora mucho más pequeños) ---

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personaMapper.toDtoList(personas); // Usar el mapper para la lista
    }

    @Transactional(readOnly = true)
    public PersonaResponseDTO getPersonaById(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona", "ID", id));
        return personaMapper.toDto(persona); // Usar el mapper para una sola entidad
    }

    @Transactional
    public PersonaResponseDTO createPersona(PersonaRequestDTO personaDto) {
        // Lógica de negocio: Verificar si ya existe una persona con el mismo DNI
        personaRepository.findByDni(personaDto.getDni())
                .ifPresent(p -> {
                    throw new DuplicateResourceException("Persona", "DNI", personaDto.getDni());
                });

    
        Persona persona = personaMapper.toEntity(personaDto); // Usar el mapper para convertir
        Persona savedPersona = personaRepository.save(persona);
        return personaMapper.toDto(savedPersona); // Usar el mapper para convertir
    }

    @Transactional
    public PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO personaDto) {
        Persona existingPersona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona", "ID", id));

        // Lógica de negocio para actualización: verificar si el nuevo DNI ya existe en otra persona
        personaRepository.findByDni(personaDto.getDni())
                .ifPresent(p -> {
                    if (!p.getIdPersona().equals(id)) {
                        throw new DuplicateResourceException("Persona", "DNI", personaDto.getDni());
                    }
                });

        // Lógica de negocio para actualización: verificar si el nuevo email ya existe en otra persona
        personaMapper.updateEntityFromDto(personaDto, existingPersona); // Usar el mapper para actualizar campos
        Persona updatedPersona = personaRepository.save(existingPersona);
        return personaMapper.toDto(updatedPersona);
    }

    @Transactional
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Persona", "ID", id);
        }
        personaRepository.deleteById(id);
    }
}