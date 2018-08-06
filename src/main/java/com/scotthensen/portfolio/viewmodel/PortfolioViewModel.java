package com.scotthensen.portfolio.viewmodel;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioViewModel 
{
	private Integer portfolioId;
	private String  portfolioName;
	private List<SecurityViewModel> securities;
	private boolean addSymbolErr;
	private String  addSymbolErrMsg;
}