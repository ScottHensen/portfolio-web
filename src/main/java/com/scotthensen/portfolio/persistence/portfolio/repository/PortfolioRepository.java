package com.scotthensen.portfolio.persistence.portfolio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioEntity;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Integer> 
{
	PortfolioEntity findPortfolioByPortfolioId(Integer portfolioId);
	
	Set<PortfolioEntity> findPortfoliosByPortfolioId(Integer portfolioId);
	
	List<PortfolioEntity> findByClientId(Integer clientId);

	@Query("select p from PortfolioEntity p left join p.securities s where p.clientId = :clientId")
	Set<PortfolioEntity> getFooByClientId(@Param("clientId") Integer clientId);
	
	@Query("select p from PortfolioEntity p join fetch p.securities s where p.portfolioId = :portfolioId")
	Set<PortfolioEntity> getFooByPortfolioId(@Param("portfolioId") Integer portfolioId);
}
