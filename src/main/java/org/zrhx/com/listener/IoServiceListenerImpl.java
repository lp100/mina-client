package org.zrhx.com.listener;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: IoServiceListenerImpl
 * @Description: TODO
 * @author: gs
 * @date: 2017年3月23日 下午2:26:42
 */
public abstract class IoServiceListenerImpl implements IoServiceListener {

	private Logger logger = LoggerFactory.getLogger(IoServiceListenerImpl.class);
	@Override
	public void serviceActivated(IoService service) throws Exception {
		logger.info("serviceActivated");

	}

	@Override
	public void serviceIdle(IoService service, IdleStatus idleStatus)
			throws Exception {
		logger.info("serviceIdle");

	}

	@Override
	public void serviceDeactivated(IoService service) throws Exception {
		logger.info("serviceDeactivated");

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {

		logger.info("sessionCreated");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("sessionClosed");
	}

	public abstract void sessionDestroyed(IoSession session) throws Exception;

}
