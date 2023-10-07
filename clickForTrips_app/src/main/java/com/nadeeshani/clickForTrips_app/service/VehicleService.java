package com.nadeeshani.clickForTrips_app.service;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;
import com.nadeeshani.clickForTrips_app.repository.BookingRepository;
import com.nadeeshani.clickForTrips_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Customer> fetchAllCustomerDetails() {
        return null;
    }

    @Override
    public List<Booking> fetchAllBookings() {
        return null;
    }
}
