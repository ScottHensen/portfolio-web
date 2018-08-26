package com.scotthensen.portfolio.persistence.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioSecurityEntity;

@Repository
public interface PortfolioSecurityRepository extends JpaRepository<PortfolioSecurityEntity, Integer> 
{

}
