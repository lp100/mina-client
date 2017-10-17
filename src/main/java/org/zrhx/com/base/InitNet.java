package org.zrhx.com.base;
/**
 * 初始化通信
 * @author gs
 *
 */
public interface InitNet {

	/**
	 * 
	 * @Title: init 初始化
	 * @Description: 初始化
	 * @param config 配置对象
	 *@param nThreads 开启的线程数
	 * @return: void
	 */
	public void init(Integer nThreads, ServerConfig config);

	/**
	 * 关闭服务
	 */
	public void closeSocket();

}
