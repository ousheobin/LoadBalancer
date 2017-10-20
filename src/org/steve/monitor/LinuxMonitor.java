package org.steve.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;

import com.sun.management.OperatingSystemMXBean;

import sun.management.ManagementFactoryHelper;
@SuppressWarnings("unused")
public class LinuxMonitor implements SystemMonitor {
	
	private OperatingSystemMXBean operatingSystemBean;
	private double CPUUsages = 1.0;
	private double memoryUsages = 1.0;

	public LinuxMonitor() {
		operatingSystemBean = (OperatingSystemMXBean) ManagementFactoryHelper.getOperatingSystemMXBean();
	}

	@Override
	public double getCPUStatus() {
		this.CPUUsages = operatingSystemBean.getSystemCpuLoad();
		return CPUUsages;
	}

	@Override
	public double getMemoryStatus() {
		memoryUsages = 1 - (double) operatingSystemBean.getFreePhysicalMemorySize()
				/ (double) operatingSystemBean.getTotalPhysicalMemorySize();
		return memoryUsages;
	}

	@Override
	public String getSystemName() {
		return "Microsoft Windows";
	}

	@Override
	public int getSystemRankAdvice() {
		int rank = 0;
		if (CPUUsages < 0.90 && memoryUsages < 0.90) {
			double weight_temp = (2-(CPUUsages+memoryUsages))/0.2;
			rank =(int) weight_temp;
		} else {
			rank = 0;
		}
		return rank;
	}

}
