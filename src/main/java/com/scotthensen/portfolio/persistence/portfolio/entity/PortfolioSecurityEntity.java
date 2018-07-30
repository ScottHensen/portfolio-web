package com.scotthensen.portfolio.persistence.portfolio.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="portfolio_securities")
public class PortfolioSecurityEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "portfolio_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PortfolioEntity portfolio;
	

	@Column(name=  "security_symbol")
	private String  symbol;
	
	@Column(name=  "security_name")
	private String  securityName;
	
	@Column(name=  "sector_name")
	private String  sector;
	
	@Column(name=  "creation_ts")
	private Date    creationTimestamp;
	
	@Column(name=  "creation_user_id")
	private Integer creationUserId;
	
	@Column(name=  "revision_ts")
	private Date    revisionTimestamp;
	
	@Column(name=  "revision_user_id")
	private Integer revisionUserId;

}
