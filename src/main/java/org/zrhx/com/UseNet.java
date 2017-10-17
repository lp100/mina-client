package org.zrhx.com;


import org.zrhx.com.base.ReceivedService;

/** 外部接口使用类
* @author gs
*
*/
public interface UseNet {
   /**
    *
    * @Title: putReceivedService
    *
    * @Description: 注册数据使用服务
    *
    * @param @param receivedServices
    *
    * @return boolean 返回类型 是否执行
    *
    * @throws
    *
    */
   public void putReceivedService(ReceivedService receivedServices);

   /**
    *
    * @Title: send
    * @Description: 发送消息
    * @param content 消息内容
    * @return: void
    */
   public boolean send(String content);

}
