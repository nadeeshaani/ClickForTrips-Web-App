package com.nadeeshani.clickForTrips_app.service;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;

import java.util.List;

public interface IVehicleService {
    List<Customer> fetchAllCustomers();
    List<Booking> fetchAllBookings();

    void saveCustomer(Customer customer);
}
