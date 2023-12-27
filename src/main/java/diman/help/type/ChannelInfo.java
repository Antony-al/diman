package diman.help.type;

public class ChannelInfo {

	private String id;
	private String name;
	private String contrAgent;
	private double rate;

	public ChannelInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChannelInfo(String id, String name, String contrAgent, double rate) {
		super();
		this.id = id;
		this.name = name;
		this.contrAgent = contrAgent;
		this.rate = rate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
