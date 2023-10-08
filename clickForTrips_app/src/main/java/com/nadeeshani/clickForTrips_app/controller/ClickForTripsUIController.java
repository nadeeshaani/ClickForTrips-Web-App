package com.nadeeshani.clickForTrips_app.controller;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;
import com.nadeeshani.clickForTrips_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClickForTripsUIController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/customer")
    public String showAllCustomers(Model model){
        model.addAttribute("customers", vehicleService.fetchAllCustomers());
        return "customer";
    }

    @GetMapping("/booking")
    public String showAllBookings(Model model){
        model.addAttribute("bookings", vehicleService.fetchAllBookings());
        return "booking";
    }

    @PostMapping("/customer/add")
    public String addCategory(Model model){
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @PostMapping("/booking/add")
    public String addBooking(Model model){
        model.addAttribute("booking", new Booking());
        model.addAttribute("customers", vehicleService.fetchAllCustomers());
        return "addBooking";
    }

    @PostMapping("/customer/save")
    public String saveCustomer(@ModelAttribute("customer")Customer customer){
        vehicleService.saveCustomer(customer);
        return "redirect:/customer";
    }

    @PostMapping("/booking/save")
    public String saveBooking(@ModelAttribute("booking") Booking booking){
        vehicleService.saveBooking(booking);
        return "redirect:/booking";
    }


    @PostMapping("/customer/delete")
    public String deleteCustomer(@RequestParam("customerId")String customerId){
        vehicleService.deleteCustomer(customerId);
        return "redirect:/customer";
    }

    @PostMapping("/booking/delete")
    public String deleteBooking(@RequestParam("bookingId")String bookingId){
        vehicleService.deleteBooking(bookingId);
        return "redirect:/booking";
    }




}
