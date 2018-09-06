package com.netposa.rom.service.scanlocaldir;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netposa.rom.service.scanlocaldir.conf.ThreadConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPool.class);

    @Autowired
    private ThreadConf threadConf;

    static int corePoolSize;

    static int maximumPoolSizeSize;

    static long keepAliveTime;

    static int queueNum;
    /**
     * 任务队列
     */


    //私有静态对象,加载时候不做初始化
    private static ThreadPoolExecutor threadPoolExecutor = null;

    synchronized public static ThreadPoolExecutor getInstance() {
        if (threadPoolExecutor == null) {
            ArrayBlockingQueue workQueue = new ArrayBlockingQueue(queueNum);
            threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSizeSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    workQueue,
                    new ThreadFactoryBuilder().setNameFormat("ThreadServer").build());
            //失败重新添加
            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        }
        return threadPoolExecutor;
    }

    private void init() {
        corePoolSize = threadConf.getCorePoolSize();
        maximumPoolSizeSize = threadConf.getMaximumPoolSizeSize();
        keepAliveTime = threadConf.getKeepAliveTime();
        queueNum = threadConf.getQueueNum();
        LOGGER.info("\n线程池配置参数:\n\tcore[{}]\n\tmax[{}]\n\tkeeptime[{}]\n\tqueueNum[{}]"
                , corePoolSize, maximumPoolSizeSize, keepAliveTime, queueNum);
    }

    @PostConstruct
    public void startrtThread() {
        init();
        //先将线程池在项目启动时初始化好
        ThreadPool.getInstance();
        LOGGER.info("线程池生成线程核心数:{}个", corePoolSize);
    }
}
