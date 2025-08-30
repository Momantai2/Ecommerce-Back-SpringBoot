package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.PersonaRequestDTO;
import com.ecommerce.ecommerce.dto.PersonaResponseDTO;
import com.ecommerce.ecommerce.service.PersonaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    private final PersonaService personaService; // Inyección del servicio

    /**
     * Maneja las solicitudes GET para obtener todas las personas.
     * URL: GET /api/personas
     * @return Una lista de PersonaResponseDTO con el estado HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> getAllPersonas() {
        List<PersonaResponseDTO> personas = personaService.getAllPersonas();
        return ResponseEntity.ok(personas); // Devuelve un estado 200 OK
    }

    /**
     * Maneja las solicitudes GET para obtener una persona por su ID.
     * URL: GET /api/personas/{id}
     * @param id El ID de la persona a buscar.
     * @return PersonaResponseDTO si se encuentra, con el estado HTTP 200 OK.
     * Lanza ResourceNotFoundException si la persona no existe (manejado por GlobalExceptionHandler).
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> getPersonaById(@PathVariable Long id) {
        PersonaResponseDTO persona = personaService.getPersonaById(id);
        return ResponseEntity.ok(persona); // Devuelve un estado 200 OK
    }

    /**
     * Maneja las solicitudes POST para crear una nueva persona.
     * URL: POST /api/personas
     * @param personaDto Los datos de la persona a crear (PersonaRequestDTO).
     * @return PersonaResponseDTO de la persona creada con el estado HTTP 201 Created.
     * Lanza DuplicateResourceException si el DNI/Email ya existe (manejado por GlobalExceptionHandler).
     * Lanza MethodArgumentNotValidException si la validación falla (manejado por GlobalExceptionHandler).
     */
    @PostMapping
    public ResponseEntity<PersonaResponseDTO> createPersona(@Valid @RequestBody PersonaRequestDTO personaDto) {
        // @Valid activa las reglas de validación definidas en PersonaRequestDTO
        // @RequestBody mapea el JSON del cuerpo de la solicitud a un objeto PersonaRequestDTO
        PersonaResponseDTO createdPersona = personaService.createPersona(personaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona); // Devuelve un estado 201 Created
    }

    /**
     * Maneja las solicitudes PUT para actualizar una persona existente.
     * URL: PUT /api/personas/{id}
     * @param id El ID de la persona a actualizar.
     * @param personaDto Los datos actualizados de la persona (PersonaRequestDTO).
     * @return PersonaResponseDTO de la persona actualizada con el estado HTTP 200 OK.
     * Lanza ResourceNotFoundException si la persona no existe.
     * Lanza DuplicateResourceException si el nuevo DNI/Email ya existe en otra persona.
     * Lanza MethodArgumentNotValidException si la validación falla.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> updatePersona(@PathVariable Long id,
                                                         @Valid @RequestBody PersonaRequestDTO personaDto) {
        PersonaResponseDTO updatedPersona = personaService.updatePersona(id, personaDto);
        return ResponseEntity.ok(updatedPersona); // Devuelve un estado 200 OK
    }

    /**
     * Maneja las solicitudes DELETE para eliminar una persona por su ID.
     * URL: DELETE /api/personas/{id}
     * @param id El ID de la persona a eliminar.
     * @return No Content (Void) con el estado HTTP 204 No Content.
     * Lanza ResourceNotFoundException si la persona no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build(); // Devuelve un estado 204 No Content
    }
}