package diman.help.type;

import java.math.BigDecimal;

public class Revenue {

	private String id;
	private BigDecimal revenue;
	private BigDecimal taxWithheldAmount;
//	private Double revenue;

//	public Revenue(String id, BigDecimal revenue, BigDecimal taxWithheldAmount) {
//		super();
//		this.id = id;
//		this.revenue = revenue;
//		this.taxWithheldAmount = taxWithheldAmount;
//	}
//	
	public Revenue(String id, BigDecimal revenue) {
		this.id = id;
		this.revenue = revenue;
	}
	
//	public Revenue(String id, Double revenue) {
//		this.id = id;
//		this.revenue = revenue;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public BigDecimal getRevenue() {
//		return revenue;
//	}
//
//	public void setRevenue(BigDecimal revenue) {
//		this.revenue = revenue;
//	}
	public BigDecimal getRevenue() {
		return revenue;
	}

	public BigDecimal getTaxWithheldAmount() {
		return taxWithheldAmount;
	}

}
