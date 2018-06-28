package com.scotthensen.portfolio.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityViewModel 
{
	private String deleteInd;
	private String symbol;
	private String bid;
	private String ask;
	private String shareQty;
	private String shareValue;
}
