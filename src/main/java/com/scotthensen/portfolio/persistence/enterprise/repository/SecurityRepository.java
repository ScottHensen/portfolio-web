package com.scotthensen.portfolio.persistence.enterprise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scotthensen.portfolio.persistence.enterprise.entity.SecurityEntity;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Integer> 
{
//	@Query("SELECT s FROM Security WHERE s.securityId = ?1")
	SecurityEntity findSecurityById(Integer securityId);

//	Optional<SecurityEntity> findSecurityBySymbol(String symbol);
	SecurityEntity findSecurityBySymbol(String symbol);

//	only enteprise security repo can do a insert/update/dete
//	SecurityEntity saveAndFlush(SecurityEntity security);
}