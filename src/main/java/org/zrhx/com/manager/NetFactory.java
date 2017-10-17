package org.zrhx.com.manager;


import org.zrhx.com.UseNet;
import org.zrhx.com.base.InitNet;

/**
 * 连接工厂类
 * @author gs
 *
 */
public class NetFactory {
	public static InitNet getInitNet(){
		return NetManager.getInstance();
	}
	
	public static UseNet getUseNet(){
		return NetManager.getInstance();
	}
}
