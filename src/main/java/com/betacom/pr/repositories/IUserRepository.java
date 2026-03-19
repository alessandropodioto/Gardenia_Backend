package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pr.models.User;

public interface IUserRepository extends JpaRepository<User, String> {

}
