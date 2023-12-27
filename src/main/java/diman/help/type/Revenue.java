package diman.help.type;

import java.math.BigDecimal;

public class Revenue {

	private String id;
	private BigDecimal revenue;

	public Revenue(String id, BigDecimal revenue) {
		super();
		this.id = id;
		this.revenue = revenue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

}
