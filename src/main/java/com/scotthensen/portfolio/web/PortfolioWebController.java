package com.scotthensen.portfolio.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioWebController 
{
	@GetMapping("/portfolio")
	public String portfolio(Model model) 
	{
		PortfolioWebViewModel portViewModel = new PortfolioWebViewModel();
		portViewModel.setTitle("Portfolio");
		portViewModel.setMessage("My Portfolio");

		List<String> hdrs = new ArrayList<>();
		hdrs.add("");
		hdrs.add("Symbol");
		hdrs.add("Bid");
		hdrs.add("Ask");
		hdrs.add("Shares");
		hdrs.add("Value");
		portViewModel.setTableHeaders(hdrs);
		
		List<SecurityViewModel> secViewModel = new ArrayList<>();
		SecurityViewModel sec1 = new SecurityViewModel();
		sec1.setDeleteInd("-");
		sec1.setSymbol("SAM");
		sec1.setBid(new BigDecimal("200.50").toPlainString());
		sec1.setAsk(new BigDecimal("201.25").toPlainString());
		sec1.setShareQty(new BigDecimal("100.00").toPlainString());
		sec1.setShareValue(new BigDecimal("20000.00").toPlainString());
		secViewModel.add(sec1);

		SecurityViewModel sec2 = new SecurityViewModel();
		sec2.setDeleteInd("-");
		sec2.setSymbol("TSLA");
		sec2.setBid(new BigDecimal("100.50").toPlainString());
		sec2.setAsk(new BigDecimal("101.25").toPlainString());
		sec2.setShareQty(new BigDecimal("1000.00").toPlainString());
		sec2.setShareValue(new BigDecimal("100000.00").toPlainString());
		secViewModel.add(sec2);

		portViewModel.setSecurities(secViewModel);
		model.addAttribute("portfolio", portViewModel);
		return "portfolio";
	}
}
