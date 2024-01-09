package diman.help.type;

import java.util.ArrayDeque;

public class RevenueContainer {

	private ArrayDeque<Revenue> list = new ArrayDeque<>();

	public RevenueContainer() {

	}

	public synchronized void add(Revenue value) {
		list.add(value);
	}

	public ArrayDeque<Revenue> getList() {
		return list;
	}

}
