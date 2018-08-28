package com.scotthensen.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteSymbolRequest 
{
	private Integer clientId;
	private Integer portfolioId;
	private String  symbol;
}
