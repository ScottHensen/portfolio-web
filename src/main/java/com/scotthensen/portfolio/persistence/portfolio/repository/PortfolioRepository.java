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
	Set<PortfolioEntity> getPortfoliosByClientId(@Param("clientId") Integer clientId);
	
	@Query("select p from PortfolioEntity p join fetch p.securities s where p.portfolioId = :portfolioId")
	Set<PortfolioEntity> getPortfoliosByPortfolioId(@Param("portfolioId") Integer portfolioId);

//	@Modifying
//	@Query("update PortfolioEntity p set p.securities = :securities where p.portfolioId = :portfolioId")
//	void addSecurity(@Param("portfolioId") Integer portfolioId, 
//				     @Param("securities")  List<PortfolioSecurityEntity> securities);
}
