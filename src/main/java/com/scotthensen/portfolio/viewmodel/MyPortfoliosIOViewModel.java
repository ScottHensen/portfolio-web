package com.scotthensen.portfolio.viewmodel;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPortfoliosIOViewModel 
{
	private MyPortfoliosViewModel originalViewModel;
	private MyPortfoliosViewModel updatedViewModel;
}
