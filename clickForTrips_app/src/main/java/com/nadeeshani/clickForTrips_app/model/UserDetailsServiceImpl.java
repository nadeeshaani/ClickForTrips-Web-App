package com.nadeeshani.clickForTrips_app.model;

import com.nadeeshani.clickForTrips_app.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo == null){
            throw new UsernameNotFoundException("User Not Found!");
        }
        return new UserDetailsImpl(userInfo);
    }
}
