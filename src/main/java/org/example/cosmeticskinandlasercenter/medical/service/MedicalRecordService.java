package org.example.cosmeticskinandlasercenter.medical.service;
import org.example.cosmeticskinandlasercenter.medical.dto.*;
import java.util.List;

public interface MedicalRecordService {

    MedicalRecordDTO createMedicalRecord(MedicalRecordRequest request);

    MedicalRecordDTO getMedicalRecordById(Long id);

    MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordUpdateRequest request);

    void deleteMedicalRecord(Long id);

    TreatmentHistoryDTO addTreatmentHistory(Long recordId, TreatmentHistoryRequest request);

    TreatmentHistoryDTO updateTreatmentHistory(Long recordId, Long historyId, TreatmentHistoryUpdateRequest request);

    void deleteTreatmentHistory(Long recordId, Long historyId);

    List<TreatmentHistoryDTO> getTreatmentHistoriesByRecordId(Long recordId);
}