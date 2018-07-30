package com.scotthensen.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scotthensen.portfolio.model.Portfolio;
import com.scotthensen.portfolio.persistence.portfolio.repository.PortfolioRepository;
import com.scotthensen.portfolio.service.PortfolioService;
import com.scotthensen.portfolio.viewmodel.MyPortfoliosViewModel;
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
	public String getPortfoliosByUserid(Model model, @PathVariable String clientId) 
	{
		List<Portfolio> portfolios = portfolioSvc.getClientPortfolios(new Integer(clientId));
		log.debug("portfolios=" + portfolios);
		
		MyPortfoliosViewModel viewModel = new MyPortfoliosViewModel();
		viewModel = viewModelMapper.buildMyPortfoliosViewModel(viewModel, portfolios);
		
		model.addAttribute("myportfolios", viewModel);
		return "myportfolios";
	}

}
