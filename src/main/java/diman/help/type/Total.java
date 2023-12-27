package diman.help.type;

import java.math.BigDecimal;

public class Total {

	private String channel;
	private String contrAgent;
	private double rate;
	private BigDecimal videoAds;
	private BigDecimal videoSubs;
	private BigDecimal nonMusSubs;
	private BigDecimal shortsAds;
	private BigDecimal shortsSubs;
	private BigDecimal rights;
	private BigDecimal taxesReturn;
	private BigDecimal taxesDebt;
	private BigDecimal total;
	private BigDecimal taxesAll;
	private TaxesDetail taxDetail;

	public Total() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Total(String channel, String contrAgent, double rate, BigDecimal videoAds, BigDecimal videoSubs,
			BigDecimal nonMusSubs, BigDecimal shortsAds, BigDecimal shortsSubs, BigDecimal rights,
			BigDecimal taxesReturn, BigDecimal taxesDebt, BigDecimal total, BigDecimal taxesAll,
			TaxesDetail taxDetail) {
		super();
		this.channel = channel;
		this.contrAgent = contrAgent;
		this.rate = rate;
		this.videoAds = videoAds;
		this.videoSubs = videoSubs;
		this.nonMusSubs = nonMusSubs;
		this.shortsAds = shortsAds;
		this.shortsSubs = shortsSubs;
		this.rights = rights;
		this.taxesReturn = taxesReturn;
		this.taxesDebt = taxesDebt;
		this.total = total;
		this.taxesAll = taxesAll;
		this.taxDetail = taxDetail;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getContrAgent() {
		return contrAgent;
	}

	public void setContrAgent(String contrAgent) {
		this.contrAgent = contrAgent;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public BigDecimal getVideoAds() {
		return videoAds;
	}

	public void setVideoAds(BigDecimal videoAds) {
		this.videoAds = videoAds;
	}

	public BigDecimal getVideoSubs() {
		return videoSubs;
	}

	public void setVideoSubs(BigDecimal videoSubs) {
		this.videoSubs = videoSubs;
	}

	public BigDecimal getNonMusSubs() {
		return nonMusSubs;
	}

	public void setNonMusSubs(BigDecimal nonMusSubs) {
		this.nonMusSubs = nonMusSubs;
	}

	public BigDecimal getShortsAds() {
		return shortsAds;
	}

	public void setShortsAds(BigDecimal shortsAds) {
		this.shortsAds = shortsAds;
	}

	public BigDecimal getShortsSubs() {
		return shortsSubs;
	}

	public void setShortsSubs(BigDecimal shortsSubs) {
		this.shortsSubs = shortsSubs;
	}

	public BigDecimal getRights() {
		return rights;
	}

	public void setRights(BigDecimal rights) {
		this.rights = rights;
	}

	public BigDecimal getTaxesReturn() {
		return taxesReturn;
	}

	public void setTaxesReturn(BigDecimal taxesReturn) {
		this.taxesReturn = taxesReturn;
	}

	public BigDecimal getTaxesDebt() {
		return taxesDebt;
	}

	public void setTaxesDebt(BigDecimal taxesDebt) {
		this.taxesDebt = taxesDebt;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTaxesAll() {
		return taxesAll;
	}

	public void setTaxesAll(BigDecimal taxesAll) {
		this.taxesAll = taxesAll;
	}

	public TaxesDetail getTaxDetail() {
		return taxDetail;
	}

	public void setTaxDetail(TaxesDetail taxDetail) {
		this.taxDetail = taxDetail;
	}

}
