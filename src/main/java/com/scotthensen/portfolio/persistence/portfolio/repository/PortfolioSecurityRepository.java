package com.scotthensen.portfolio.persistence.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioEntity;
import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioSecurityEntity;

@Repository
public interface PortfolioSecurityRepository extends JpaRepository<PortfolioSecurityEntity, Integer> 
{
	@Modifying
	@Query("delete from PortfolioSecurityEntity s where s.portfolio = :portfolio and s.symbol = :symbol")
	void deleteBySymbol(@Param("portfolio") PortfolioEntity portfolio, @Param("symbol") String symbol);
	
}
