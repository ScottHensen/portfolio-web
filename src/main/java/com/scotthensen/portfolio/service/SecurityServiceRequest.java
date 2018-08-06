package com.scotthensen.portfolio.service;

import org.springframework.stereotype.Component;

import com.scotthensen.portfolio.model.Security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class SecurityServiceRequest 
{
	SecurityServiceRequest (String symbol)
	{
		this.symbol = symbol;
	}
	public Security security;
	public String   symbol;
}
