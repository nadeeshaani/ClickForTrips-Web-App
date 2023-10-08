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
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
}
