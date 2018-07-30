package com.scotthensen.portfolio.persistence.portfolio.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="portfolios")
public class PortfolioEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=  "portfolio_id")
	private Integer id;
	
	@Column(name=  "portfolio_name")
	private String  name;
	
	@Column(name=  "client_id")
	private Integer clientId;
	
	@Column(name=  "avatar_id")
	private Integer avatarId;
	
	//private AuditData auditData; ...google JPA Audit 
	//                             ...https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
	
	@Column(name=  "creation_ts")
	private Date    creationTimestamp;
	
	@Column(name=  "creation_user_id")
	private Integer creationUserId;
	
	@Column(name=  "revision_ts")
	private Date    revisionTimestamp;
	
	@Column(name=  "revision_user_id")
	private Integer revisionUserId;
}
