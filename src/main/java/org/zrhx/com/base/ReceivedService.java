package org.zrhx.com.base;


import org.zrhx.com.entity.DataFrame;

/**
 * 处理业务接口 (外部具体实现)
 * @author gs
 *
 */
public interface ReceivedService {
	/**
	 * 
	 * @Title: received
	 * @Description: 接受处理上报服务
	 * @param frame
	 * @return
	 * @return: boolean 返回是否执行
	 */
	public boolean received(DataFrame frame);
}
