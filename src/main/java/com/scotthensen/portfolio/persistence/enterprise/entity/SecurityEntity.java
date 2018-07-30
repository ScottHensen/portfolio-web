package com.scotthensen.portfolio.persistence.enterprise.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="securities")
public class SecurityEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=  "security_id")
	private Integer id;
	
	@Column(name=  "symbol_id")
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
