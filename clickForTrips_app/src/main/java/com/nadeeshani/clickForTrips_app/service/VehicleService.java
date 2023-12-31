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
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Booking> fetchAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> fetchBookingsByUsername(String username) {
        return bookingRepository.findByUsername(username);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(Long.valueOf(bookingId));

    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(Long.valueOf(customerId));
    }

    @Override
    public Customer fetchCustomer(String customerId) {
        return customerRepository.findById(Long.valueOf(customerId)).orElse(null);
    }

    @Override
    public Booking fetchBooking(String bookingId) {
        return bookingRepository.findById(Long.valueOf(bookingId)).orElse(null);
    }
}
