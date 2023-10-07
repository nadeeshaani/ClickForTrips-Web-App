package com.nadeeshani.clickForTrips_app.controller;

import com.nadeeshani.clickForTrips_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
