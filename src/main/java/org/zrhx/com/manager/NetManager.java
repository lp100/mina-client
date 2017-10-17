package org.zrhx.com.manager;


import org.apache.log4j.Logger;
import org.zrhx.com.UseNet;
import org.zrhx.com.base.*;
import org.zrhx.com.entity.DataFrame;
import org.zrhx.com.impl.MinaClient;


/**
 * 连接服务管理类
 * @author gs
 *
 */
public class NetManager implements InitNet,UseNet {
	/**执行业务*/
	private ReceivedTask receivedTask;
	
	protected Logger logger = Logger.getLogger(NetManager.class);
	
	private MinaClient client;
	
	private static NetManager instance=null;
	
	
	private NetManager(){
		client = MinaClient.getInstance(); 
	}
	
	synchronized static NetManager getInstance() {
		if(null==instance){
			instance = new NetManager();
		}
		return instance;
	}

	@Override
	public void putReceivedService(ReceivedService receivedServices) {
		receivedTask.putReceivedService(receivedServices);
		
	}

	@Override
	public void init(Integer nThreads,ServerConfig config) {
		
		if(null==nThreads||0==nThreads){
			logger.error("初始化处理业务线程数不正确");
			throw new RRException("初始化处理业务线程数不正确");
		}
		if(null == config){
			logger.error("参数配置出错");
			throw new RRException("参数配置出错");
		}
		receivedTask = new ReceivedTask(nThreads);
		if(null!=config.getTaskCallback()){
			config.getTaskCallback().excute();
		}
		if(null==config||null==config.getPort()){
			logger.error("socket端口号为空");
			throw new RRException("socket端口号为空"+config);
		}

		config.setReceivedHandler(new DataReceivedHandler() {
			@Override
			public void onReceived(DataFrame frame) {
				receivedTask.received(frame);
			}
		});
		client.init(config);
		logger.info("socket连接成功！");
		receivedTask.start();
		logger.info("初始化成功！");
	}

	@Override
	public void closeSocket() {
		client.close();
	}


	@Override
	public boolean send(String content) {
		return client.send(content);
	}


}
