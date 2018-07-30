package com.scotthensen.portfolio.service;

import java.util.List;
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
}
