package com.mdababi.bookstore.repository;

import com.mdababi.bookstore.domain.security.ERole;
import com.mdababi.bookstore.domain.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
