package com.nadeeshani.clickForTrips_app.controller;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;
import com.nadeeshani.clickForTrips_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClickForTripsAPIController {
    @Autowired
    private VehicleService vehicleService;

    @RequestMapping("/customer")
    List<Customer> fetchAllCustomers() {
        return vehicleService.fetchAllCustomers();
    }

    @RequestMapping("/booking")
    List<Booking> fetchAllBookings(){
        return vehicleService.fetchAllBookings();
    }
}
