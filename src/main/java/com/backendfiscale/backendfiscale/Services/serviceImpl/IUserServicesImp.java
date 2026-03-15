package com.backendfiscale.backendfiscale.Services.serviceImpl;


import com.backendfiscale.backendfiscale.Repository.IUserRepository;
import com.backendfiscale.backendfiscale.Services.Iservices.IUserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class IUserServicesImp implements IUserServices {

    private final IUserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String s) {
                return userRepository.findByEmail(s).orElseThrow(() -> new RuntimeException("User not found"));
            }
        };
    }
}
