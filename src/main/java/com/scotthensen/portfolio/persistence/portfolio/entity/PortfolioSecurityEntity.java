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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="security")
public class PortfolioSecurityEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=  "id")
	private Integer id;
	
	@Column(name=  "security_id")
	private Integer securityId;
	
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

	@JoinColumn(name = "portfolio_id")
	@ManyToOne(targetEntity = PortfolioEntity.class, fetch = FetchType.LAZY) //, cascade = CascadeType.ALL)
	private PortfolioEntity portfolio; 
		
	//Lombok's toString causes an infinite recursive one-to-many - many-to-one fetch, so overriding it
	@Override
	public String toString()
	{
		return "PortfolioSecurityEntity [ "
					+ " id = "        		   + id
					+ ", securityId = "        + securityId
				    + ", symbol = "            + symbol
				    + ", securityName = "      + securityName
				    + ", sector = "            + sector
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
        return id != null && id.equals(((PortfolioSecurityEntity) o).id);
    }
    
    @Override
    public int hashCode() {
        return 8;
    }


}
