package org.example.cosmeticskinandlasercenter.patient.mapper;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientDTO;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientRequest;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface using MapStruct to convert between Patient entity and DTO/Request objects.
 * Simplifies the process of mapping fields between different object types.
 */
@Mapper(componentModel = "spring") // Instructs MapStruct to create a Spring component for this mapper
public interface PatientMapper {

    /**
     * Converts a Patient entity to a PatientDTO.
     * @param patient The Patient entity to convert.
     * @return PatientDTO representing the entity.
     */
    PatientDTO toDTO(Patient patient);

    /**
     * Converts a PatientRegistrationRequest to a new Patient entity.
     * @param request The PatientRegistrationRequest object.
     * @return A new Patient entity populated from the request.
     */
    Patient toEntity(PatientRequest request);

    /**
     * Updates an existing Patient entity with data from PatientUpdateRequest.
     * @param request The PatientUpdateRequest object containing update data.
     * @param patient The target Patient entity to be updated.
     * @return The updated Patient entity.
     */
    Patient toEntity(PatientUpdateRequest request, @MappingTarget Patient patient);
}