package org.zrhx.com.base;
/**
 * 客户端
 * @ClassName: IMinaClient
 * @Description: TODO
 * @author: gs
 * @date: 2017年3月17日 上午11:36:33
 */
public interface IMinaClient {
	
	/**
	 * 发送消息
	 * @Title: send
	 * @Description: TODO
	 * @param content
	 * @return
	 * @return:void
	 */
	boolean  send(String content);
	/**
	 * 
	 * @Title: close
	 * @Description: 关闭socket (长连接  不建议使用)
	 * @return: void
	 */
	void close();
	
	/**
	 * 初始化
	 * @Title: init
	 * @Description: TODO
	 * @param config
	 * @return: void
	 */
	public void init(ServerConfig config) ;
}
