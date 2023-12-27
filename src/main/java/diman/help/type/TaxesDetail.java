package diman.help.type;

import java.math.BigDecimal;

public class TaxesDetail {

	private BigDecimal adsRev;
	private BigDecimal subsRev;
	private BigDecimal shortsAdsRev;
	private BigDecimal shortsSubsRev;
	private BigDecimal superThanks;
	private BigDecimal transOthers;

	public TaxesDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaxesDetail(BigDecimal adsRev, BigDecimal subsRev, BigDecimal shortsAdsRev, BigDecimal shortsSubsRev,
			BigDecimal superThanks, BigDecimal transOthers) {
		super();
		this.adsRev = adsRev;
		this.subsRev = subsRev;
		this.shortsAdsRev = shortsAdsRev;
		this.shortsSubsRev = shortsSubsRev;
		this.superThanks = superThanks;
		this.transOthers = transOthers;
	}

	public BigDecimal getAdsRev() {
		return adsRev;
	}

	public void setAdsRev(BigDecimal adsRev) {
		this.adsRev = adsRev;
	}

	public BigDecimal getSubsRev() {
		return subsRev;
	}

	public void setSubsRev(BigDecimal subsRev) {
		this.subsRev = subsRev;
	}

	public BigDecimal getShortsAdsRev() {
		return shortsAdsRev;
	}

	public void setShortsAdsRev(BigDecimal shortsAdsRev) {
		this.shortsAdsRev = shortsAdsRev;
	}

	public BigDecimal getShortsSubsRev() {
		return shortsSubsRev;
	}

	public void setShortsSubsRev(BigDecimal shortsSubsRev) {
		this.shortsSubsRev = shortsSubsRev;
	}

	public BigDecimal getSuperThanks() {
		return superThanks;
	}

	public void setSuperThanks(BigDecimal superThanks) {
		this.superThanks = superThanks;
	}

	public BigDecimal getTransOthers() {
		return transOthers;
	}

	public void setTransOthers(BigDecimal transOthers) {
		this.transOthers = transOthers;
	}

}
