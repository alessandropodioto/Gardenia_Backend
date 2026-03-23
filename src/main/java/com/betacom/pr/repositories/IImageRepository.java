package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pr.models.Image;

public interface IImageRepository extends JpaRepository<Image, Integer> {
}