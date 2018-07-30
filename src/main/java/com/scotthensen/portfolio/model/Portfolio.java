package com.scotthensen.portfolio.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio 
{
	private Integer id;
	private String  name;
	private Integer clientId;
	private Integer avatarId;
	private List<Security> securities;
}
