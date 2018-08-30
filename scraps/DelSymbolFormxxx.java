package com.scotthensen.portfolio.viewmodel.form;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class DelSymbolFormxxx 
{
	public DelSymbolFormxxx() {
		this.message      = "";
		this.messageStyle = "default";
		this.hasError     = false;
	}
	
	private Integer portfolioId;
	private String  symbol;
	private String  message;
	private String  messageStyle;
	private Boolean hasError;

}
