package com.scotthensen.portfolio.persistence.portfolio.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="portfolio")
public class PortfolioEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=  "portfolio_id")
	private Integer portfolioId;
	
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
	
	@OneToMany(targetEntity  = PortfolioSecurityEntity.class, 
			   mappedBy      = "portfolio", 
			   cascade       = CascadeType.ALL,
			   orphanRemoval = true)
	private List<PortfolioSecurityEntity> securities = new ArrayList<>(); 
	
	
	
	public void addSecurity(PortfolioSecurityEntity security)
	{
		securities.add(security);
		security.setPortfolio(null);
	}
	public void removeSecurity(PortfolioSecurityEntity security)
	{
		securities.remove(security);
		security.setPortfolio(null);
	}

	//Lombok's toString causes an infinite recursive one-to-many - many-to-one fetch, so overriding it
	@Override
	public String toString()
	{
		return "PortfolioEntity [ "
					+ " portfolioId = "        + portfolioId
				    + ", name = "              + name
				    + ", clientId = "          + clientId
				    + ", avatarId = "          + avatarId
				    + ", creationTimestamp = " + creationTimestamp
				    + ", creationUserId = "    + creationUserId
				    + ", revisionTimestamp = " + revisionTimestamp
				    + ", revisionUserId = "    + revisionUserId
				    + " ]";
	}
	
	//Lombok's equals and hashCode are also likely to cause issues, so overriding them too
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioEntity )) return false;
        return portfolioId != null && portfolioId.equals(((PortfolioEntity) o).portfolioId);
    }
    
    @Override
    public int hashCode() {
        return 42;
    }


}
