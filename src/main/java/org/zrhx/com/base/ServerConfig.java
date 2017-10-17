package org.zrhx.com.base;
import org.apache.mina.filter.codec.ProtocolCodecFactory;


/**
 * 服务配置
 * @author gs
 *
 */
public class ServerConfig {
	
	private Integer port;//端口号
	
	private String serverIp;//服务端ip
	
	private ProtocolCodecFactory codecFactory;
	
	private DataReceivedHandler receivedHandler;
	
	private ConnectionCallback callback;
	
	private ReceivedTaskCallback taskCallback;
	
	private int timeOut = 3000;//链路超时时间（秒）
	
	private int IdleTime = 5000;//心跳时间
	
	private int nThreads = 5;//默认线程池树

	

	public ServerConfig(int port, String serverIp,
			ProtocolCodecFactory codecFactory,
			DataReceivedHandler receivedHandler, ConnectionCallback callback,
			int nThreads) {
		super();
		this.port = port;
		this.serverIp = serverIp;
		this.codecFactory = codecFactory;
		this.receivedHandler = receivedHandler;
		this.callback = callback;
		this.nThreads = nThreads;
	}
	
	public ServerConfig(int port, String serverIp,
			ProtocolCodecFactory codecFactory,
			ConnectionCallback callback) {
		super();
		this.port = port;
		this.serverIp = serverIp;
		this.codecFactory = codecFactory;
		this.callback = callback;
	}
	public ServerConfig(int port, String serverIp, ConnectionCallback callback) {
		super();
		this.port = port;
		this.serverIp = serverIp;
		this.callback = callback;
	}
	public ProtocolCodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(ProtocolCodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	public DataReceivedHandler getReceivedHandler() {
		return receivedHandler;
	}

	public void setReceivedHandler(DataReceivedHandler receivedHandler) {
		this.receivedHandler = receivedHandler;
	}

	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public int getnThreads() {
		return nThreads;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getIdleTime() {
		return IdleTime;
	}

	public void setIdleTime(int idleTime) {
		IdleTime = idleTime;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

	public ConnectionCallback getCallback() {
		return callback;
	}

	public void setCallback(ConnectionCallback callback) {
		this.callback = callback;
	}

	public ReceivedTaskCallback getTaskCallback() {
		return taskCallback;
	}

	public void setTaskCallback(ReceivedTaskCallback taskCallback) {
		this.taskCallback = taskCallback;
	}

	@Override
	public String toString() {
		return "ServerConfig{" +
				"port=" + port +
				", serverIp='" + serverIp + '\'' +
				", codecFactory=" + codecFactory +
				", receivedHandler=" + receivedHandler +
				", callback=" + callback +
				", taskCallback=" + taskCallback +
				", timeOut=" + timeOut +
				", IdleTime=" + IdleTime +
				", nThreads=" + nThreads +
				'}';
	}
}
