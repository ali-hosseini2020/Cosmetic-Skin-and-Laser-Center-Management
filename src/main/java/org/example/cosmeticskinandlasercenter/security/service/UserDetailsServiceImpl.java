package org.example.cosmeticskinandlasercenter.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.staff.repository.StaffRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StaffRepository staffRepository; // Assuming Staff is used for authentication

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByEmail(username) // Or findByUsername if you use username
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = staff.getStaffRoles().stream() // Assuming Staff has a getStaffRoles() that returns a list of roles (enums or strings)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())) // "ROLE_" prefix is convention for Spring Security roles
                .collect(Collectors.toList());

        return new User(staff.getEmail(), staff.getPassword(), authorities); // Create Spring Security UserDetails
    }
}