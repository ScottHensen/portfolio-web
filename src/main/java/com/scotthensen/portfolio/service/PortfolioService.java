package com.scotthensen.portfolio.service;

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
					.map(p -> mapPortfolioEntityToModel(p))
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
						.enterpriseSecurityId(response.getSecurity().getId())	
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

	public void addSecurityToPortfolio(Portfolio portfolio, Security security, Integer securityId) 
	{
		log.debug("addSecurity...portfolio=" + portfolio);
		log.debug("addSecurity...security =" + security);
		
		//TOOD:  the model doesn't include cre/revsn cols, so we can't map to entity; we have to select it.
		//       do I add them to bus model or give up on splitting model & entity?
		//       PortfolioEntity portfolioEntity = portfolioModelToEntityMapper(portfolio);
		
		PortfolioEntity portfolioEntity = portfolioRepo.getOne(portfolio.getId());

		PortfolioSecurityEntity securityEntity = mapPortfolioSecurityModelToEntity(security);
		securityEntity.setSecurityId(securityId);
		securityEntity.setPortfolio(portfolioEntity);
		//TODO:  need to get userid for clientId
		securityEntity.setCreationUserId(1);  			

		portfolioSecurityRepo.save(securityEntity);
		
		log.debug("securityEntityA="+securityEntity);
		log.debug("portfolioEntityA="+portfolioEntity);
	}

	public Optional<Portfolio> getPortfolio(Integer portfolioId) 
	{
		Optional<PortfolioEntity> portfolioEntity = portfolioRepo.findById(portfolioId);
		
		return Optional.ofNullable(mapPortfolioEntityToModel(portfolioEntity.get()));
	}
	
	public void delSecurityInPortfolio(Integer portfolioId, String symbol)
	{
		PortfolioEntity portfolio = portfolioRepo.findPortfolioByPortfolioId(portfolioId);
		
		portfolioSecurityRepo.deleteBySymbol(portfolio, symbol);
	}
	
	private Portfolio mapPortfolioEntityToModel(PortfolioEntity p) 
	{
		return Portfolio
					.builder()
					.id(p.getPortfolioId())
					.name(p.getName())
					.clientId(p.getClientId())
					.avatarId(p.getAvatarId())
					.securities(p.getSecurities()
									.stream()
									.map(s -> mapPortfolioSecurityEntityToModel(s))
									.peek(s -> log.debug("securityMapper: " + s.getSymbol()))
									.collect(Collectors.toList()))
					.build();
	}
	
	private PortfolioEntity mapPortfolioModelToEntity(Portfolio p) 
	{
		PortfolioEntity portfolioEntity = new PortfolioEntity();
		portfolioEntity.setPortfolioId(p.getId());
		portfolioEntity.setAvatarId(p.getAvatarId());
		portfolioEntity.setClientId(p.getClientId());
		portfolioEntity.setName(p.getName());
		portfolioEntity.setSecurities(p.getSecurities()
										.stream()
										.map(s -> mapPortfolioSecurityModelToEntity(s))
										.collect(Collectors.toList()));
		return portfolioEntity;
	}

	private Security mapPortfolioSecurityEntityToModel(PortfolioSecurityEntity s) 
	{
		return Security
					.builder()
					.id(s.getSecurityId())
					.sector(s.getSector())
					.securityName(s.getSecurityName())
					.symbol(s.getSymbol())
					.build();
	}

	private PortfolioSecurityEntity mapPortfolioSecurityModelToEntity(Security s) 
	{
		PortfolioSecurityEntity portfolioSecurityEntity = new PortfolioSecurityEntity();
		portfolioSecurityEntity.setSector(s.getSector());;
		portfolioSecurityEntity.setSecurityName(s.getSecurityName());;
		portfolioSecurityEntity.setSymbol(s.getSymbol());;
		return portfolioSecurityEntity;
	}

}
