package com.scotthensen.portfolio.viewmodel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.scotthensen.portfolio.viewmodel.form.AddSymbolForm;

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
	private Integer        clientId;
	private List<String>   tableHeaders;
	private List<PortfolioViewModel> portfolios;
	private AddSymbolForm  addSymbolForm;   
}
