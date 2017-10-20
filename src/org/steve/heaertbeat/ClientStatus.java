package org.steve.heaertbeat;

public class ClientStatus {

	private double cpuUsages;
	private double memoryUsages;
	private double currentRank;
	private boolean isRun = true;

	public synchronized double getCpuUsages() {
		return cpuUsages;
	}

	public synchronized void setCpuUsages(double cPUUsages) {
		cpuUsages = cPUUsages;
	}

	public synchronized double getMemoryUsages() {
		return memoryUsages;
	}

	public synchronized void setMemoryUsages(double memoryUsages) {
		this.memoryUsages = memoryUsages;
	}

	public synchronized double getCurrentRank() {
		return currentRank;
	}

	public synchronized void setCurrentRank(double currentRank) {
		this.currentRank = currentRank;
	}

	public synchronized boolean isRun() {
		return isRun;
	}

	public synchronized void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public String toString() {
		return "ClientStatus [CPUUsages=" + cpuUsages + ", memoryUsages=" + memoryUsages + ", currentRank="
				+ currentRank + ", isRun=" + isRun + "]";
	}

}
