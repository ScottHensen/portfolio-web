package com.scotthensen.portfolio.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioWebViewModel 
{
	private String         title;
	private String         message;
	private List<String>   tableHeaders;
	private List<SecurityViewModel> securities;
}
