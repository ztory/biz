package com.ztory.lib.biz;

import android.os.Build;

import java.io.Closeable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BizExecutor is used for providing Executor instances.
 * The execute() function can be used to produce data in a simple, asyncronous and structured way.
 * Created by jonruna on 2017-02-01.
 */
public class BizExecutor {

    private static final Executor
            sHttpExecutor = createExecutor(BizExecutor.class.getName() + ".http", 8),
            sImageExecutor = createExecutor(BizExecutor.class.getName() + ".image", 4);

    public static Executor getHttpExecutor() {
        return sHttpExecutor;
    }

    public static Executor getImageExecutor() {
        return sImageExecutor;
    }

    public static <T, D> void execute(
            final Executor executor,
            final BizProduce<D> rawDataProducer,
            final BizCallbackProduce<T, D> returnDataProducer,
            final BizCallback<T> callbackSuccess,
            final BizCallback<BizException> callbackFailure
    ) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                D rawData = null;
                try {
                    rawData = rawDataProducer.produce();

                    T returnData = returnDataProducer.callbackProduce(rawData);
                    if (callbackSuccess != null) {
                        callbackSuccess.callback(returnData);
                    }
                } catch (Exception e) {
                    if (callbackFailure != null) {
                        if (rawData != null) {
                            callbackFailure.callback(new BizException(rawData, e));
                        }
                        else {
                            callbackFailure.callback(new BizException("rawData == null", e));
                        }
                    }
                } finally {
                    if (rawData != null) {
                        try {
                            if (rawData instanceof Closeable) {
                                ((Closeable) rawData).close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            //SWALLOW EXCEPTION
                        }
                    }
                }
            }
        });
    }

    public static ThreadPoolExecutor createExecutor(
            final String threadNamePrefix,
            int poolSizeMax
    ) {
        ThreadPoolExecutor returnPool = new ThreadPoolExecutor(
                poolSizeMax,
                poolSizeMax,
                4L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactory() {
                    private final AtomicInteger mCount = new AtomicInteger(1);
                    public Thread newThread(Runnable runnable) {
                        return new Thread(
                                runnable,
                                threadNamePrefix + " #" + mCount.getAndIncrement()
                        );
                    }
                }
        );
        if (Build.VERSION.SDK_INT >= 9) {
            returnPool.allowCoreThreadTimeOut(true);
        }
        return returnPool;
    }

}
