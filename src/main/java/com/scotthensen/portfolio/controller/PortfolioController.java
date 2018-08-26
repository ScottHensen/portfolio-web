package com.scotthensen.portfolio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioRepository;
import com.scotthensen.portfolio.service.PortfolioService;
import com.scotthensen.portfolio.service.PortfolioServiceResponse;
import com.scotthensen.portfolio.viewmodel.MyPortfoliosViewModel;
import com.scotthensen.portfolio.viewmodel.form.AddSymbolForm;
import com.scotthensen.portfolio.viewmodel.mapper.ViewModelMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PortfolioController 
{
	@Autowired 
	PortfolioService portfolioSvc;
	
	@Autowired 
	PortfolioRepository portfolioRepo;
	
	@Autowired 
	ViewModelMapper viewModelMapper;
	
	@GetMapping("/portfolios/{clientId}")
	public String showPortfoliosForClientId(Model model, @PathVariable String clientId) 
	{
		List<Portfolio> portfolios = 
				portfolioSvc.getClientPortfolios(new Integer(clientId));
		
		MyPortfoliosViewModel viewModel = 
				viewModelMapper.buildMyPortfoliosViewModel(new MyPortfoliosViewModel(), portfolios, new AddSymbolForm());

		model.addAttribute("originalViewModel", viewModel);
		log.debug("model="+model);
		return "myportfolios";
	}
	
	@PostMapping("/portfolios/{clientId}")
	public String handleMyPortfoliosForClientFormSubmit(
														@PathVariable String clientId,
														@ModelAttribute MyPortfoliosViewModel originalViewModel,
														BindingResult errors,
														Model model)
	{
		log.debug("  model=" + model.toString());
		log.debug("ogmodel=" + originalViewModel.toString());

		MyPortfoliosViewModel viewModel = originalViewModel;
		AddSymbolForm addSymbolForm = viewModel.getAddSymbolForm();  
		
		Optional<Portfolio> portfolio = 							
				portfolioSvc.getPortfolio(viewModel.getAddSymbolForm().getPortfolioId());

		if (portfolio.isPresent()) 
		{
			PortfolioServiceResponse portfolioSvcResponse = 		// and this
				portfolioSvc.getSecurity(viewModel.getAddSymbolForm().getSymbol());
			
			if (portfolioSvcResponse.isSuccess()) 
			{
				portfolioSvc.addSecurityToPortfolio(portfolio.get(), 
													portfolioSvcResponse.getSecurity(), 
													portfolioSvcResponse.getSecurityId());				
			}
			else {
				log.error(portfolioSvcResponse.getMessage());
				addSymbolForm.setHasError(true);
				addSymbolForm.setMessage(portfolioSvcResponse.getMessage());
				addSymbolForm.setMessageStyle("error");
			}
		}
		else {
			log.error("error will robinson!");
		}

		List<Portfolio> portfolios = portfolioSvc.getClientPortfolios(new Integer(clientId));
		log.debug("updated portfolios=" + portfolios);
		
		viewModel = viewModelMapper.buildMyPortfoliosViewModel(viewModel, portfolios, addSymbolForm, originalViewModel);

		model.addAttribute("originalViewModel", viewModel);
		log.debug("updated viewModel=" + viewModel);

		return "myportfolios";
	}

}
