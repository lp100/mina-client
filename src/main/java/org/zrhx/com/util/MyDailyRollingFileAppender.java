package org.zrhx.com.util;

import org.apache.log4j.DailyRollingFileAppender;

import java.io.File;
/**  
 * @Title: MyDailyRollingFileAppender
 * @Description:  日志生成
 * @author: gs  
 * @date: 2017/11/23 11:15 
 */
public class MyDailyRollingFileAppender extends DailyRollingFileAppender {
    @Override
    public void setFile(String file) {
        String filePath = file;
        File fileCheck = new File(filePath);
        if (!fileCheck.exists()) {
            fileCheck.getParentFile().mkdirs();
        }
        super.setFile(filePath);
    }
}
