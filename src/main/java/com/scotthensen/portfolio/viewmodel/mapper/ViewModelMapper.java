package com.scotthensen.portfolio.viewmodel.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.model.Security;
import com.scotthensen.portfolio.viewmodel.MyPortfoliosViewModel;
import com.scotthensen.portfolio.viewmodel.PortfolioViewModel;
import com.scotthensen.portfolio.viewmodel.SecurityViewModel;
import com.scotthensen.portfolio.viewmodel.form.AddSymbolForm;

@Component
public class ViewModelMapper {

	@Autowired 
	SecurityViewModel securityViewModel;
	@Autowired
	PortfolioViewModel portfolioViewModel;
	
	public MyPortfoliosViewModel buildMyPortfoliosViewModel(
									MyPortfoliosViewModel viewModel, 
									List<Portfolio> portfolios) 
	{
	viewModel.setTitle("Portfolios");
	viewModel.setMessage("My Portfolios");
	viewModel.setClientId(1);	//TODO: fix this
	viewModel.setAddSymbolForm(new AddSymbolForm());
	viewModel.setTableHeaders(buildPortfolioHeaders());
	viewModel.setPortfolios(mapPortfoliosToPortfolioViewModelList(
				viewModel.getPortfolios(), 
				portfolios));
	
	return viewModel;
	}
	
	public MyPortfoliosViewModel buildMyPortfoliosViewModel(
									MyPortfoliosViewModel viewModel, 
									List<Portfolio> portfolios,
									AddSymbolForm form) 
	{
	viewModel.setTitle("Portfolios");
	viewModel.setMessage("My Portfolios");
	viewModel.setClientId(1);	//TODO: fix this
	viewModel.setAddSymbolForm(form);
	viewModel.setTableHeaders(buildPortfolioHeaders());
	viewModel.setPortfolios(mapPortfoliosToPortfolioViewModelList(
				viewModel.getPortfolios(), 
				portfolios));
	
	return viewModel;
	}

	private List<String> buildPortfolioHeaders() 
	{
		List<String> hdrs = new ArrayList<>();
		
		hdrs.add("");
		hdrs.add("Symbol");
		hdrs.add("Bid");
		hdrs.add("Ask");
		hdrs.add("Shares");
		hdrs.add("Value");
		
		return hdrs;
	}

	private List<PortfolioViewModel> mapPortfoliosToPortfolioViewModelList(
										List<PortfolioViewModel> portfolioViewModelList, 
										List<Portfolio> portfolios) 
	{
		portfolioViewModelList = 
				portfolios.stream()
						  .map(p -> mapPortfolioToViewModel(p))
						  .collect(Collectors.toList());
				
		return portfolioViewModelList;
	}

	private PortfolioViewModel mapPortfolioToViewModel(Portfolio p) 
	{
		PortfolioViewModel portfolioViewModel = new PortfolioViewModel();
		
		portfolioViewModel.setPortfolioId(p.getId());
		portfolioViewModel.setPortfolioName(p.getName());
		portfolioViewModel.setSecurities(mapSecuritiesToSecurityViewModelList(
											portfolioViewModel.getSecurities(), 
											p.getSecurities()));
		
		return portfolioViewModel;
	}
	
	private List<SecurityViewModel> mapSecuritiesToSecurityViewModelList(
										List<SecurityViewModel> securityViewModelList,
										List<Security> securities) 
	{
		securityViewModelList = 
				securities.stream()
				  		  .map(s -> mapSecurityToViewModel(s))
				  		  .collect(Collectors.toList());
		
		return securityViewModelList;
	}
	
	private SecurityViewModel mapSecurityToViewModel(Security security)
	{
		SecurityViewModel securityViewModel = new SecurityViewModel();
		
		securityViewModel.setDeleteInd("-");
		securityViewModel.setSymbol(security.getSymbol());
		securityViewModel.setName(security.getSecurityName());
		securityViewModel.setNameVisibleInd("N");
		securityViewModel.setSector(security.getSector());
		securityViewModel.setSectorVisibleInd("N");
		
		return securityViewModel;
	}

}
