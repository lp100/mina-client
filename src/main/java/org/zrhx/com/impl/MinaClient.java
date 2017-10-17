package org.zrhx.com.impl;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zrhx.com.base.IMinaClient;
import org.zrhx.com.base.ServerConfig;
import org.zrhx.com.handler.MinaClientHandler;
import org.zrhx.com.listener.IoServiceListenerImpl;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * mina客户端
 * @ClassName: MinaClient
 * @Description: TODO
 * @author: gs
 * @date: 2017年3月17日 上午11:20:54
 */
public class MinaClient implements IMinaClient {
	
	private Logger logger = LoggerFactory.getLogger(MinaClient.class);
	
	private NioSocketConnector connector;
	
	private IoSession ioSession;
	
	private ServerConfig config;
	
	private MinaClient() {
		
	}
	
	private static class SingletonHolder{
		private final static MinaClient INSTANCE = new MinaClient();
	}
	
	public static MinaClient getInstance(){
		return SingletonHolder.INSTANCE;
				
	}
    /**
     * 初始化
     * @Title: init
     * @Description: TODO
     * @param config
     * @return: void
     */
    @Override
	public void init(ServerConfig config) {
		this.config = config;
		connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();  
		//设定这个过滤器
        chain.addLast("myChin", new ProtocolCodecFilter(config.getCodecFactory()));
        //客户端的消息处理器：一个SamplMinaServerHander对象     
        connector.setHandler(new MinaClientHandler(config));
        connector.setConnectTimeoutMillis(config.getTimeOut());
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, config.getIdleTime());        
        // 添加重连监听  
        connector.addListener(new IoServiceListenerImpl() {
			@Override
			public void sessionDestroyed(IoSession session) throws Exception {
				content();
			}  
		});
        //连接到服务器：     
        connector.setDefaultRemoteAddress(new InetSocketAddress(config.getServerIp(),config.getPort())); 
 	    content();
	}
    /**
     * 连接
     * @Title: content
     * @Description: TODO
     * @return
     * @return: boolean
     */
	private void content(){
		for (;;) {  
            try {  
                ConnectFuture future = connector.connect();
				// 等待连接创建成功
                future.awaitUninterruptibly();
                IoSession session = future.getSession();
                if (session.isConnected()) {
					logger.error("断线重连[{}:{}]成功",connector.getDefaultRemoteAddress().getHostName(), connector.getDefaultRemoteAddress().getPort());
                    this.ioSession = session;
                    config.getCallback().callback(this);
               	 	break;  
                }  
            } catch (RuntimeIoException ex) {  
           	  logger.error("重连[{}:{}]服务器登录失败,5秒再连接一次:" + ex.getMessage(),connector.getDefaultRemoteAddress().getHostName(), connector.getDefaultRemoteAddress().getPort());
           	  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error( ex.getMessage(),e); 
			}
            }  
        } 
		
	}



	@Override
	public boolean send(String content) {
		if(null==ioSession){
		   logger.error("socket还未连接......." ); 
		}
		if(ioSession.isConnected()){
			ioSession.write(content);
			return true;
		}else{
			logger.error("socket已经断开连接......." +ioSession.isConnected()); 
			ioSession.close(true);
		}
		return false;
	}


	@Override
	public void close() {
		if(null==ioSession){
		   logger.error("socket还未连接......." ); 
		}
		ioSession.close(true);
	}


}
