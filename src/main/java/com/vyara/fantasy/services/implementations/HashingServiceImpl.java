package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.services.HashingService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HashingServiceImpl implements HashingService {

    private final PasswordEncoder passwordEncoder;


    @Override
    public String hashPass(String str) {
        return passwordEncoder.encode(str);
    }


}
