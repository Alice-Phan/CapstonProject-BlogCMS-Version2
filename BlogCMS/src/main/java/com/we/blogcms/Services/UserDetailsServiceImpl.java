package com.we.blogcms.Services;

import com.we.blogcms.dao.AuthorDao;
import com.we.blogcms.model.Author;
import com.we.blogcms.model.Role1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthorDao authorDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorDao.readByUsername(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role1 role1 : author.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role1.getRole1()));
        }


        return new org.springframework.security.core.userdetails.User(author.getEmail(), author.getPassword(), grantedAuthorities);
    }
}
