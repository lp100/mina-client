package org.zrhx.com.manager;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.zrhx.com.base.ReceivedService;
import org.zrhx.com.entity.DataFrame;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 执行业务
 * @author gs
 *
 */
public class ReceivedTask {
	
	private Logger log = Logger.getLogger(ReceivedTask.class);
	/**接受消息集合*/
	private ConcurrentLinkedQueue<DataFrame> frames;
	/***接受处理服务*/
	private ConcurrentLinkedQueue<ReceivedService> services;
	/***带返回的服务**/
//	private Map<String,ResultCallBack> receivedMap;
	
	private int runs = 1;
	
	private int nThreads = 1;
	
	/**接受数据线程
	private ReceivedThread[] receivedThread;*/
	ExecutorService fixedThreadPool;
	
	ReceivedTask(int nThreads) {
		this.nThreads=nThreads;
		frames = new ConcurrentLinkedQueue<DataFrame>();
		services = new ConcurrentLinkedQueue<ReceivedService>();
		fixedThreadPool = Executors.newFixedThreadPool(nThreads);  
	   
	}
	/**
	 * 
	 * @Title: start 
	 *
	 * @Description: TODO(执行接受的数据线程) 
	 *
	 * @param 
	 *
	 * @return void    返回类型 
	 *
	 * @throws 
	 *
	 */
	public void start(){
		fixedThreadPool.execute(new ReceivedThread(this));
	}

	/**
	 * 注册服务
	 * @param receivedServices
	 */
	void putReceivedService(ReceivedService receivedServices) {
		services.add(receivedServices);
	}
	/**
	 * 
	 * @Title: isStoped 
	 *
	 * @Description: TODO(判断获取接受的数据线程是否都停止) 
	 *
	 * @param @return
	 *
	 * @return boolean    返回类型 
	 *
	 * @throws 
	 *
	 */
	boolean isStoped() {
		return runs<1;
	}

	/**
	 * 接受消息 放入队列 内部使用
	 * @param frame
	 * @return
	 */
	synchronized boolean received(DataFrame frame) {
		if (!isStoped()) {
			this.frames.add(frame);
		}
		return !isStoped();
	}

	/**
	 * 从缓存队列取出消息 并开启相应的线程数
	 * @param r
	 * @return
	 */
	private synchronized DataFrame exectueRun(ReceivedThread r){
		DataFrame frame = frames.poll();
		int size = frames.size();
		int cnThreads=size/10+1;
		if(cnThreads>=nThreads){
			if(runs<nThreads){
				start();
            	runs++;
            	log.error(size+":"+runs);
        		log.error(Thread.currentThread().getName());
			}
		}else{
			if(runs<cnThreads){
				start();
            	runs++;
            	log.error(size+":"+runs);
        		log.error(Thread.currentThread().getName());
			}else if(runs>cnThreads){
				r.setStoped(true);
            	runs--;
            	log.error(size+":"+runs);
        		log.error(Thread.currentThread().getName());
			}
		}
		return frame;
	}


	protected void execute(ReceivedThread r){
		DataFrame frame = exectueRun(r);
		if (null != frame) {
			try {
				for (ReceivedService servce : services) {
					boolean b = servce.received(frame);
					if(b){
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("线程解析分发错误!!!",e);
			}
		}
	}
}
