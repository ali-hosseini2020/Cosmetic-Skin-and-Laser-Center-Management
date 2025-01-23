package org.example.cosmeticskinandlasercenter.treatment.service;
import org.example.cosmeticskinandlasercenter.treatment.dto.*;
import java.util.List;

/**
 * Service interface for Treatment management operations.
 */
public interface TreatmentService {

    TreatmentDTO createTreatment(TreatmentRequest request);

    TreatmentDTO getTreatmentById(Long id);

    List<TreatmentDTO> getAllTreatments();

    TreatmentDTO updateTreatment(Long id, TreatmentUpdateRequest request);

    void deleteTreatment(Long id);

    TreatmentPlanDTO createTreatmentPlan(TreatmentPlanRequest request);

    TreatmentPlanDTO getTreatmentPlanById(Long id);

    List<TreatmentPlanDTO> getAllTreatmentPlans();

    TreatmentPlanDTO updateTreatmentPlan(Long id, TreatmentPlanUpdateRequest request);

    void deleteTreatmentPlan(Long id);
}