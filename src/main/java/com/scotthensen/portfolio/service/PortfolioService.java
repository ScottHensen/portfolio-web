package com.scotthensen.portfolio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.model.Security;
import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioEntity;
import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioSecurityEntity;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioRepository;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioSecurityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("portfolioTransactionManager")
public class PortfolioService {

	@Autowired
	PortfolioRepository portfolioRepo;
	
	@Autowired
	PortfolioSecurityRepository portfolioSecurityRepo;
	
	@Autowired
	SecurityService securitySvc;
	
	public List<Portfolio> getClientPortfolios(Integer clientId)
	{
		Set<PortfolioEntity> portfolioEntities = 
				portfolioRepo.getFooByClientId(clientId);

		log.debug("portfolioEntites=" + portfolioEntities);

		return portfolioEntities
					.stream()
					.map(p -> portfolioEntityToModelMapper(p))
					.peek(p -> log.debug("portfolioMapper: " + p.getName()))
					.collect(Collectors.toList());
	}

	
	//TODO: make this optional
	public PortfolioServiceResponse getSecurity(String symbol) 
	{
		SecurityServiceResponse response = securitySvc.getSecurity(new SecurityServiceRequest(symbol));
		
		if (response.isSuccess()) {
			return PortfolioServiceResponse
						.builder()
						.security(response.getSecurity())
						.success(true)
						.message("Success: security ("+symbol+") was found by SecurityService.")
						.build();
		}
		else {
			return PortfolioServiceResponse
						.builder()
						.security(new Security())
						.success(false)
						.message("Failure: security ("+symbol+") was not found by SecurityService.")
						.build();

		}
	}

	public void addSecurityToPortfolio(Portfolio portfolio, Security security) 
	{
		//TODO:  I'm doing this wrong.
		//       I need to do portfolioEntity.addSecurity, then saveandflush the PORTFOLIO.
		//       probably need to add list<security> to portfolio entity and rework the relationship annotations
		//       remember, these objects map relationships; they do not have to match db implementation
		log.debug("addSecurity...portfolio=" + portfolio);
		log.debug("addSecurity...security =" + security);
		
		PortfolioSecurityEntity portfolioSecurityEntity = portfolioSecurityModelToEntityMapper(security);
		
//		PortfolioEntity portfolioEntity = portfolioModelToEntityMapper(portfolio);
//		portfolioEntity.getSecurities().add(portfolioSecurityEntity);
//		
//		portfolioRepo.save(portfolioEntity);
		
		
		
		
		
		
		
//		portfolioRepo.saveAndFlush(portfolioEntity);
		
//		PortfolioEntity portfolioEntity = portfolioModelToEntityMapper(portfolio);
//		portfolioEntity.setCreationUserId(portfolio.getClientId());
//		
//		PortfolioSecurityEntity portfolioSecurityEntity = portfolioSecurityModelToEntityMapper(security);
//		portfolioSecurityEntity.setPortfolio(portfolioEntity);
//		portfolioSecurityEntity.setCreationUserId(portfolio.getClientId());
//		
//		portfolioSecurityRepo.saveAndFlush(portfolioSecurityEntity);
	}

	public Optional<Portfolio> getPortfolio(Integer portfolioId) 
	{
		Optional<PortfolioEntity> portfolioEntity = portfolioRepo.findById(portfolioId);
		
		return Optional.ofNullable(portfolioEntityToModelMapper(portfolioEntity.get()));
	}
	
	
	private Portfolio portfolioEntityToModelMapper(PortfolioEntity p) 
	{
		return Portfolio
					.builder()
					.id(p.getPortfolioId())
					.name(p.getName())
					.clientId(p.getClientId())
					.avatarId(p.getAvatarId())
					.securities(p.getSecurities()
									.stream()
									.map(s -> portfolioSecurityEntityToModelMapper(s))
									.peek(s -> log.debug("securityMapper: " + s.getSymbol()))
									.collect(Collectors.toList()))
					.build();
	}
	
	private PortfolioEntity portfolioModelToEntityMapper(Portfolio p) 
	{
		PortfolioEntity portfolioEntity = new PortfolioEntity();
		portfolioEntity.setPortfolioId(p.getId());
		portfolioEntity.setAvatarId(p.getAvatarId());
		portfolioEntity.setClientId(p.getClientId());
		portfolioEntity.setName(p.getName());
		return portfolioEntity;
	}

	private Security portfolioSecurityEntityToModelMapper(PortfolioSecurityEntity s) 
	{
		return Security
					.builder()
					.id(s.getSecurityId())
					.sector(s.getSector())
					.securityName(s.getSecurityName())
					.symbol(s.getSymbol())
					.build();
	}

	private PortfolioSecurityEntity portfolioSecurityModelToEntityMapper(Security s) 
	{
		PortfolioSecurityEntity portfolioSecurityEntity = new PortfolioSecurityEntity();
		portfolioSecurityEntity.setSecurityId(s.getId());
		portfolioSecurityEntity.setSector(s.getSector());;
		portfolioSecurityEntity.setSecurityName(s.getSecurityName());;
		portfolioSecurityEntity.setSymbol(s.getSymbol());;
		return portfolioSecurityEntity;
	}

}
