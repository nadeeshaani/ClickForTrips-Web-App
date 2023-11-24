package com.nadeeshani.clickForTrips_app.controller;

import com.nadeeshani.clickForTrips_app.model.Booking;
import com.nadeeshani.clickForTrips_app.model.Customer;
import com.nadeeshani.clickForTrips_app.service.VehicleService;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.user.User;
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
import java.util.List;
import java.util.Map;

@Controller
public class ClickForTripsUIController {
    @Autowired
    private VehicleService vehicleService;


    //checked working for both
    @GetMapping("/clickfortrips/")
    String index(){
        return "index";
    }


    //checked -> /clickfortrips isn't coming
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/customer")
    public String showAllCustomers(Model model){
        model.addAttribute("customers", vehicleService.fetchAllCustomers());
        return "customer";
    }

    //checked -> /clickfortrips isn't coming
    @GetMapping("/booking")
    public String showUserBookings(Model model, @AuthenticationPrincipal OAuth2User user) {
        String username = user.getAttribute("sub");
        List<Booking> userBookings = vehicleService.fetchBookingsByUsername(username);
        model.addAttribute("bookings", userBookings);
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
    public String userInfo(@AuthenticationPrincipal OAuth2User oauth2User) {
        String name = oauth2User.getAttribute("name");
        String userEmail = oauth2User.getAttribute("email");
        String userId = oauth2User.getAttribute("sub");
        //https://dev-17371279-admin.okta.com/api/v1/users/00udcvpczthtxOmwL5d7
        // Replace with your actual Okta domain and API token
        String oktaDomain = "https://dev-17371279.okta.com";
        String oktaApiToken = "0098cOcvNXzzJomPEQC7qe__QZ-9R0j33wnuO1ebvk";

        try {
            // Initialize the Okta client
            Client oktaClient = Clients.builder()
                    .setOrgUrl(oktaDomain)
                    .setClientCredentials(new TokenClientCredentials(oktaApiToken))
                    .build();

            // Get the User object from Okta using the user ID
            User oktaUser = oktaClient.getUser(userId);

            // Extract the required attributes from the Okta User object
            String country = oktaUser.getProfile().get("country").toString();
            String mobilePhone = oktaUser.getProfile().get("mobilePhone").toString();
            String city = oktaUser.getProfile().get("city").toString();

            // Construct HTML content for displaying the profile information
            return  "<p>Username: <br>" + userId + "</p>" +
                    "<p>Name: <br>" + name + "</p>" +
                    "<p>Email: <br>" + userEmail + "</p>" +
                    "<p>Country: <br>" + country + "</p>" +
                    "<p>Mobile Phone: <br>" + mobilePhone + "</p>" +
                    "<p>City: <br>" + city + "</p>";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return "Error fetching user profile"; // Return an error message to the client
        }
    }

}
