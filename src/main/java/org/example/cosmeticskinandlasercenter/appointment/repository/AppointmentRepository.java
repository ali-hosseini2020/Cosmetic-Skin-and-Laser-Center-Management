package org.example.cosmeticskinandlasercenter.appointment.repository;

import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}