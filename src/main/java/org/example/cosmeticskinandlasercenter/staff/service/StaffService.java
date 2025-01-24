package org.example.cosmeticskinandlasercenter.staff.service;
import org.example.cosmeticskinandlasercenter.staff.dto.*;
import java.util.List;

public interface StaffService {
    StaffDTO createStaff(StaffRequest staffRequest);
    SpecialistDTO createSpecialist(SpecialistRequest specialistRequest);
    StaffDTO getStaffById(Long id);
    SpecialistDTO getSpecialistById(Long id);
    List<StaffDTO> getAllStaff();
    List<SpecialistDTO> getAllSpecialists();
    StaffDTO updateStaff(Long id, StaffUpdateRequest staffUpdateRequest);
    SpecialistDTO updateSpecialist(Long id, SpecialistUpdateRequest specialistUpdateRequest);
    void deleteStaff(Long id);
}