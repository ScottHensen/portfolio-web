package com.scotthensen.portfolio.service;

import org.springframework.stereotype.Component;

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
public class SecurityServiceResponse 
{
	private Security security;
	private Boolean  success;
	private String   message;
	
	public boolean isSuccess()
	{
		return success;
	}
}
