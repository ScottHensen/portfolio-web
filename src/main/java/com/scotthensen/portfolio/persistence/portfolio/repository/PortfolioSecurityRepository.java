package com.scotthensen.portfolio.persistence.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioSecurityEntity;

@Repository
public interface PortfolioSecurityRepository extends JpaRepository<PortfolioSecurityEntity, Integer> 
{
//	List<PortfolioSecurityEntity> findByPortfolioId(Integer portfolioId);
//
//	List<PortfolioSecurityEntity> findSecuritiesByPortfolioId(Integer portfolioId);
}
