package org.steve.heaertbeat;

import org.steve.monitor.SystemMonitor;

public class MonitorHeartbeat implements Runnable{
	
	private SystemMonitor systemMonitor;
	private ClientStatus clientStatus;
	
	public MonitorHeartbeat(SystemMonitor systemMonitor,ClientStatus clientStatus){
		this.clientStatus = clientStatus;
		this.systemMonitor = systemMonitor;
	}

	@Override
	public void run() {
		while(true){
			clientStatus.setCpuUsages(systemMonitor.getCPUStatus());
			clientStatus.setMemoryUsages(systemMonitor.getMemoryStatus());
			clientStatus.setCurrentRank(systemMonitor.getSystemRankAdvice());
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
