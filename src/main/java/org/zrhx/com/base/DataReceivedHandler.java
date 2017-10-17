package org.zrhx.com.base;


import org.zrhx.com.entity.DataFrame;

/**
 * 接受信息回调类
 * @author gs
 *
 */
public interface DataReceivedHandler {
	
	void onReceived(DataFrame frame);
}
