package com.scotthensen.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scotthensen.portfolio.model.Security;
import com.scotthensen.portfolio.persistence.enterprise.entity.SecurityEntity;
import com.scotthensen.portfolio.persistence.enterprise.repository.SecurityRepository;

@Service
@Transactional("enterpriseTransactionManager")
public class SecurityService 
{
	@Autowired
	SecurityRepository securityRepo;
	
	//TODO: probably should be optional; what happens if the save fails?  
	//      throw exception and handle in caller.
	public SecurityServiceResponse getSecurity(SecurityServiceRequest request) 
	{
		//Optional<SecurityEntity> securityEntity = Optional.ofNullable(securityRepo.findSecurityBySymbol(symbol));
		SecurityEntity securityEntity = securityRepo.findSecurityBySymbol(request.getSymbol());
		
		if (securityEntity != null) {
			return SecurityServiceResponse
						.builder()
						.security(securityEntityToModelMapper(securityEntity))
						.success(true)
						.message("Success: security ("+request.getSymbol()+") was found on Enterprise Security Database.")
						.build();
		}
		else {
			return SecurityServiceResponse
						.builder()
						.security(new Security())
						.success(false)
						.message("Failure: security ("+request.getSymbol()+") was not found on Enterprise Security Database.")
						.build();
		}
	}

	private Security securityEntityToModelMapper(SecurityEntity s) 
	{
		return Security
					.builder()
					.id(s.getId())
					.sector(s.getSector())
					.securityName(s.getSecurityName())
					.symbol(s.getSymbol())
					.build();
	}

}
