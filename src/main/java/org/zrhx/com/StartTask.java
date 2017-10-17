package org.zrhx.com;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.zrhx.com.base.ConnectionCallback;
import org.zrhx.com.base.IMinaClient;
import org.zrhx.com.base.ServerConfig;
import org.zrhx.com.manager.NetFactory;

import java.nio.charset.Charset;

/**
 * 
 * @ClassName: StartTask
 * @Description: 初始化上报的执行任务
 * @author: gs
 * @date: 2017年6月2日 下午4:48:07
 */
public class StartTask {
	

	public StartTask() {
		ServerConfig config = new ServerConfig(8555, "172.16.75.1", new ConnectionCallback() {

			@Override
			public void callback(IMinaClient iMinaClient) {

			}
		});
		config.setCodecFactory(new TextLineCodecFactory(Charset.forName("UTF-8")));
		 NetFactory.getInitNet().init(5,config);
       }

	public static void main(String[] args) {
		new StartTask();
	}
}
