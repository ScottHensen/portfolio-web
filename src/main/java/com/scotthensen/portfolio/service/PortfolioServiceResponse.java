package com.scotthensen.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.model.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioServiceResponse 
{
	private List<Portfolio> portfolios;
	private List<Security>  securities;
	private Integer   enterpriseSecurityId;
	private Security  security;
	private Portfolio portfolio;
	private Boolean   success;
	private String    message;
	
	public boolean isSuccess()
	{
		return success;
	}

}
