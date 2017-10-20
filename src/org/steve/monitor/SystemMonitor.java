package org.steve.monitor;

public interface SystemMonitor {
	
	/**
	 * 获取CPU运算占用状况
	 * @return CPU效率指标
	 */
	public double getCPUStatus();
	/**
	 * 获取内存使用情况
	 * @return 内存指标
	 */
	public double getMemoryStatus();
	/**
	 * 返回当前操作系统类型
	 * @return 操作系统类型
	 */
	public String getSystemName();
	/**
	 * 客户端根据实际占用情况评估权重
	 * @return 评估权重值
	 */
	public int getSystemRankAdvice();

}
