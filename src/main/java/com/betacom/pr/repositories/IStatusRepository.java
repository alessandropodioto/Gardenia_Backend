package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.betacom.pr.models.Status;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Integer> {
	
}