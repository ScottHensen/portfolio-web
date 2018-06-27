package com.scotthensen.portfolio.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioWebController 
{
	@GetMapping("/portfolio")
	public String portfolio(Model model) 
	{
		PortfolioWebViewModel viewModel = new PortfolioWebViewModel();
		viewModel.setTitle("Hello");
		viewModel.setMessage("Sup girl?");
		model.addAttribute("portfolio", viewModel);
		return "portfolio";
	}
}
