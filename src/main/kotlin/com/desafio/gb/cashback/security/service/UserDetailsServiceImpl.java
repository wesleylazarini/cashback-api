package com.desafio.gb.cashback.security.service;

import com.desafio.gb.cashback.entity.dto.RevendedorDto;
import com.desafio.gb.cashback.service.RevendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    RevendedorService revendedorService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RevendedorDto revendedor = Optional.ofNullable(revendedorService.buscaPoremail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(revendedor);
    }

}