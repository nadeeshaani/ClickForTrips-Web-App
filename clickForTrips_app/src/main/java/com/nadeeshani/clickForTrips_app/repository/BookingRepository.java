package com.nadeeshani.clickForTrips_app.repository;

import com.nadeeshani.clickForTrips_app.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
//    List<Booking> findAllByOrderByBookingId();
}