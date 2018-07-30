package com.scotthensen.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security 
{
	private Integer id;
	private String  symbol;
	private String  securityName;
	private String  sector;
}
