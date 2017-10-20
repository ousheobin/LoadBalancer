package org.steve.monitor;

public interface SystemMonitor {
	
	/**
	 * ��ȡCPU����ռ��״��
	 * @return CPUЧ��ָ��
	 */
	public double getCPUStatus();
	/**
	 * ��ȡ�ڴ�ʹ�����
	 * @return �ڴ�ָ��
	 */
	public double getMemoryStatus();
	/**
	 * ���ص�ǰ����ϵͳ����
	 * @return ����ϵͳ����
	 */
	public String getSystemName();
	/**
	 * �ͻ��˸���ʵ��ռ���������Ȩ��
	 * @return ����Ȩ��ֵ
	 */
	public int getSystemRankAdvice();

}
