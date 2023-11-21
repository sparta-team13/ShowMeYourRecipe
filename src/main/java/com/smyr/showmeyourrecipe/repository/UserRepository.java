package com.smyr.showmeyourrecipe.repository;

import com.smyr.showmeyourrecipe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Long > {
}
