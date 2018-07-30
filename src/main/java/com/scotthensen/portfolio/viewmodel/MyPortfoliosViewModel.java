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
public class MyPortfoliosViewModel 
{
	private String         title;
	private String         message;
	private List<String>   tableHeaders;
	private List<PortfolioViewModel> portfolios;
}
