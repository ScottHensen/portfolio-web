package com.scotthensen.portfolio.persistence.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.enterprise.entity.SecurityEntity;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Integer> 
{
	SecurityEntity findSecurityById(Integer securityId);

	SecurityEntity findSecurityBySymbol(String symbol);
}
