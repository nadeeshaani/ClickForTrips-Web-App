package com.nadeeshani.clickForTrips_app.service;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;

import java.util.List;

public interface IVehicleService {
    List<Customer> fetchAllCustomers();
    List<Booking> fetchAllBookings();

    public List<Booking> fetchBookingsByUsername(String username);

    void saveCustomer(Customer customer);

    void saveBooking(Booking booking);

    void deleteBooking(String bookingId);

    void deleteCustomer(String customerId);

    Customer fetchCustomer(String customerId);

    Booking fetchBooking(String bookingId);
}
