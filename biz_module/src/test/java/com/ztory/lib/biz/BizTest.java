package com.ztory.lib.biz;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class BizTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBizSetterGetter() throws Exception {
        final String testString = "StringValue";
        assertNull(Biz.get(String.class));
        Biz.set(String.class, testString);
        assertNotNull(Biz.get(String.class));
        assertEquals(Biz.get(String.class), testString);
        Biz.set(String.class, null);
        assertNull(Biz.get(String.class));
    }

    public void testBizCastOrNull() throws Exception {
        Object castObject = 1;
        assertNull(Biz.castOrNull(String.class, castObject));
        assertNotNull(Biz.castOrNull(Integer.class, castObject));

        castObject = "Hello Cast World!";
        assertNull(Biz.castOrNull(Integer.class, castObject));
        assertNotNull(Biz.castOrNull(String.class, castObject));
    }

    public void testBizExecutor() throws Exception {

        final String produceString = "VeryImportantStringText!";

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicBoolean successAtomBool = new AtomicBoolean(false);
        final AtomicReference<Integer> atomicReference = new AtomicReference<>();
        BizExecutor.execute(
                BizExecutor.getHttpExecutor(),
                new BizProduce<String>() {
                    @Override
                    public String produce() throws Exception {
                        return produceString;
                    }
                },
                new BizCallbackProduce<Integer, String>() {
                    @Override
                    public Integer callbackProduce(String param) throws Exception {
                        return param.length();
                    }
                },
                new BizCallback<Integer>() {
                    @Override
                    public void callback(Integer param) {
                        countDownLatch.countDown();
                        successAtomBool.set(true);
                        atomicReference.set(param);
                    }
                },
                new BizCallback<BizException>() {
                    @Override
                    public void callback(BizException param) {
                        countDownLatch.countDown();
                    }
                }
        );

        countDownLatch.await();

        assertTrue("BizExecutor.execute() resulted in ERROR", successAtomBool.get());
        assertNotNull(atomicReference.get());
        assertEquals(atomicReference.get(), Integer.valueOf(produceString.length()));
    }

}
