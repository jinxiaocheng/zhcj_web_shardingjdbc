package com.escst.commons.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dwj
 * @desc
 * @date 13:17 2018/2/23
 */
public class SimpleThreadPool {

    private static SimpleThreadPool threadPool = null;

    public ExecutorService executorService = null;

    private SimpleThreadPool(){
        executorService = Executors.newCachedThreadPool();
    }

    public static synchronized SimpleThreadPool getInstance(){
        if(threadPool == null){
            threadPool = new SimpleThreadPool();
        }
        return threadPool;
    }

}
