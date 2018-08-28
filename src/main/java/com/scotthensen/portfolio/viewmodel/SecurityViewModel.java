package com.scotthensen.portfolio.viewmodel;

import org.springframework.stereotype.Component;

import com.scotthensen.portfolio.viewmodel.form.DelSymbolForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityViewModel 
{
	//private String deleteInd;
	private String symbol;
	private String name;
	private String nameVisibleInd;
	private String sector;
	private String sectorVisibleInd;
	private String bid;
	private String ask;
	private String shareQty;
	private String shareValue;
	//private DelSymbolForm deleteSymbolForm;
}
