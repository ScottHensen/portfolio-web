package com.scotthensen.portfolio.persistence.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioEntity;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Integer> 
{
	List<PortfolioEntity> findPortfoliosById(Integer portfolioId);
	
	List<PortfolioEntity> findPortfoliosByClientId(Integer clientId);
}
