package com.nadeeshani.clickForTrips_app.controller;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;
import com.nadeeshani.clickForTrips_app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class ClickForTripsUIController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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

//    @PostMapping("/booking/save")
//    public String saveBooking(@ModelAttribute("booking") Booking booking) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//            Date parsedDate = sdf.parse(booking.getTime());
//            Time sqlTime = new Time(parsedDate.getTime());
//            booking.setTime(sqlTime.toString());
//        } catch (ParseException e) {
//            // Handle parsing exception
//            return "error-page"; // Redirect to an error page or handle it appropriately
//        }
//
//        vehicleService.saveBooking(booking);
//        return "redirect:/booking";
//    }

        @PostMapping("/booking/save")
    public String saveBooking(@ModelAttribute("booking") Booking booking, @AuthenticationPrincipal OAuth2User user) {

        // Set Okta username to the booking entity
            String oktaUsername = user.getAttribute("sub");
            if (oktaUsername != null && !oktaUsername.isEmpty()) {
                booking.setUsername(oktaUsername);
            } else {
                // Handle the case when Okta username is empty or null
                return "error-page"; // Redirect to an error page or handle it appropriately
            }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date parsedDate = sdf.parse(booking.getTime());
            Time sqlTime = new Time(parsedDate.getTime());
            booking.setTime(sqlTime.toString());
        } catch (ParseException e) {
            // Handle parsing exception
            return "error-page"; // Redirect to an error page or handle it appropriately
        }

        vehicleService.saveBooking(booking);
        return "redirect:/booking";
    }



    @PostMapping("/customer/edit")
    public String editCustomer(@RequestParam("customerId")String customerId, Model model){
        model.addAttribute("customer", vehicleService.fetchCustomer(customerId));
        return "editCustomer";
    }

    @PostMapping("/booking/edit")
    public String editBooking(@RequestParam("bookingId")String bookingId, Model model){
        model.addAttribute("booking", vehicleService.fetchBooking(bookingId));
        model.addAttribute("category", vehicleService.fetchAllCustomers());
        return "editBooking";
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

    @RequestMapping("/oauthinfo")
    @ResponseBody
    public String oauthUserInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                @AuthenticationPrincipal OAuth2User oauth2User) {
        return
                "User Name: " + oauth2User.getName() + "<br/>" +
                        "User Authorities: " + oauth2User.getAuthorities() + "<br/>" +
                        "Client Name: " + authorizedClient.getClientRegistration().getClientName() + "<br/>" +
                        this.prettyPrintAttributes(oauth2User.getAttributes());
    }

    private String prettyPrintAttributes(Map<String, Object> attributes) {
        String acc = "User Attributes: <br/><div style='padding-left:20px'>";
        for (String key : attributes.keySet()){
            Object value = attributes.get(key);
            acc += "<div>"+key + ":&nbsp" + value.toString() + "</div>";
        }
        return acc + "</div>";
    }


    @RequestMapping("/userinfo")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal OAuth2User user) {
        String name = user.getAttribute("name");
        String userEmail = user.getAttribute("email");
        String userCountry = null;
        String userPhoneNumber = null;
        String username = user.getAttribute("sub");

        // Accessing the 'address' attribute to retrieve country
        Map<String, Object> addressMap = (Map<String, Object>) user.getAttribute("address");
        if (addressMap != null) {
            userCountry = (String) addressMap.get("country");
        }

        // Accessing the 'phone' attribute to retrieve phone number
        userPhoneNumber = user.getAttribute("phone_number");

        // Returning the user information
        // Construct HTML content for userinfo
        return  "<p>Username: " + username + "</p>" +
                "<p>Name: " + name + "</p>" +
                "<p>=Email: " + userEmail + "</p>" +
                "<p>Country: " + userCountry + "</p>" +
                "<p>Phone Number: " + userPhoneNumber + "</p>";

    }











}
