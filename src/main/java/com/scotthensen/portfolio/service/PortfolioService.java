package com.scotthensen.portfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.model.Security;
import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioEntity;
import com.scotthensen.portfolio.persistence.portfolio.entity.PortfolioSecurityEntity;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioRepository;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioSecurityRepository;

@Service
public class PortfolioService {

	@Autowired
	PortfolioRepository portfolioRepo;
	
	@Autowired
	PortfolioSecurityRepository portfolioSecurityRepo;
	
	@Autowired
	SecurityService securitySvc;
	
	
	public List<Portfolio> getClientPortfolios(Integer clientId)
	{
		List<PortfolioEntity> portfolioEntities = 
				portfolioRepo.findPortfoliosByClientId(clientId);
		
		return portfolioEntities
					.stream()
					.map(p -> portfolioEntityToModelMapper(p))
					.collect(Collectors.toList());
	}

	public List<Security> getSecuritiesHeldInPortfolio(Integer portfolioId)
	{
		List<PortfolioSecurityEntity> portfolioSecurityEntities = 
				portfolioSecurityRepo.findSecuritiesByPortfolioId(portfolioId);
		
		return portfolioSecurityEntities
					.stream()
					.map(s -> portfolioSecurityEntityToModelMapper(s))
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
		PortfolioEntity portfolioEntity = portfolioModelToEntityMapper(portfolio);
		portfolioEntity.setCreationUserId(portfolio.getClientId());

		PortfolioSecurityEntity portfolioSecurityEntity = portfolioSecurityModelToEntityMapper(security);
		portfolioSecurityEntity.setPortfolio(portfolioEntity);
		portfolioSecurityEntity.setCreationUserId(portfolio.getClientId());
		
		portfolioSecurityRepo.saveAndFlush(portfolioSecurityEntity);
	}

	public Optional<Portfolio> getPortfolio(Integer portfolioId) 
	{
		Optional<PortfolioEntity> portfolioEntity = portfolioRepo.findById(portfolioId);
		
		return Optional.ofNullable(portfolioEntityToModelMapper(portfolioEntity.get()));
//		if (portfolioEntity.isPresent()) {
//			return portfolioEntityToModelMapper(portfolioEntity.get());			
//		}
//		else {
//			return Optional.empty();
//		}
	}
	
	
	private Portfolio portfolioEntityToModelMapper(PortfolioEntity p) 
	{
		return Portfolio
					.builder()
					.id(p.getId())
					.name(p.getName())
					.clientId(p.getClientId())
					.avatarId(p.getAvatarId())
					.securities(getSecuritiesHeldInPortfolio(p.getId()))
					.build();
	}
	
	private PortfolioEntity portfolioModelToEntityMapper(Portfolio p) 
	{
		PortfolioEntity portfolioEntity = new PortfolioEntity();
		portfolioEntity.setId(p.getId());
		portfolioEntity.setAvatarId(p.getAvatarId());
		portfolioEntity.setClientId(p.getClientId());
		portfolioEntity.setName(p.getName());
		return portfolioEntity;
	}

	private Security portfolioSecurityEntityToModelMapper(PortfolioSecurityEntity s) 
	{
		return Security
					.builder()
					.id(s.getId())
					.sector(s.getSector())
					.securityName(s.getSecurityName())
					.symbol(s.getSymbol())
					.build();
	}

	private PortfolioSecurityEntity portfolioSecurityModelToEntityMapper(Security s) 
	{
		PortfolioSecurityEntity portfolioSecurityEntity = new PortfolioSecurityEntity();
		portfolioSecurityEntity.setId(s.getId());
		portfolioSecurityEntity.setSector(s.getSector());;
		portfolioSecurityEntity.setSecurityName(s.getSecurityName());;
		portfolioSecurityEntity.setSymbol(s.getSymbol());;
		return portfolioSecurityEntity;
	}

}
