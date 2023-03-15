package com.mycapstone.masonsoccer.security;

import com.mycapstone.masonsoccer.data.AuthGroupRepoI;
import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.models.AuthGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {

    CoachRepoI coachRepoI;

    AuthGroupRepoI authGroupRepoI;
    @Autowired
    public AppUserDetailService(CoachRepoI coachRepoI, AuthGroupRepoI authGroupRepoI) {
        this.coachRepoI = coachRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return new AppUserPrincipal(coachRepoI.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Email not found"))
                ,authGroupRepoI.findByEmail(username));
    }
}
