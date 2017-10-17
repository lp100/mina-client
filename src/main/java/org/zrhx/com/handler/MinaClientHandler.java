package org.zrhx.com.handler;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zrhx.com.base.ServerConfig;
import org.zrhx.com.entity.DataFrame;

import java.net.InetSocketAddress;

/**
 * 作为线程运行，负责接受来自服务端的请求
 * @author gs
 *
 */
public class MinaClientHandler implements IoHandler {

	private static final Logger logger = LoggerFactory.getLogger(MinaClientHandler.class);
	
	private ServerConfig config;
	
	public MinaClientHandler(ServerConfig config) {
		this.config  = config;
	}
	/**
	 * 捕获异常时
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable e)
			throws Exception {
		e.printStackTrace();
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
		session.close(true);
		logger.error(e.getMessage(), e);
		logger.error("{}会话出现异常{}",clientIP,e.toString());
	}
    /**
     * 接受到数据时
     */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
		int port = inetSocketAddress.getPort();
		logger.info("接受{}:{}的到数据:{}",clientIP,port,message);
		config.getReceivedHandler().onReceived((DataFrame)message);
	}
    /**
     * 消息调用IoSession.write写的(对象)
     */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("消息调用IoSession.write写的(对象)"+message);
	}
	/**
	 * 在关闭连接时调用，包括自己关闭和客户端关闭
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
		int port = inetSocketAddress.getPort();
		logger.error("{}:{}会话关闭连接",clientIP,port);
	}
    /**
     * session创建时
     */
    @Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("消息调用IoSession.write写的(对象)");
	}
	
	/**
	 * 当设置了idletime时，会定时调用该方法
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus arg1)
			throws Exception {
	}
    /**
     * 
     */
	@Override
	public void sessionOpened(final IoSession session) throws Exception {
		logger.error("消息调用sessionOpened");
		
	}
	/**
	 * 
	 */
	@Override
	public void inputClosed(IoSession session) throws Exception {
		session.close(true);
		logger.error("inputClosed");
	}


}
