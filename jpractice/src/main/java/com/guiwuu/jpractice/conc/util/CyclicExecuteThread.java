package com.guiwuu.jpractice.conc.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CyclicExecuteThread extends Thread {

    private static final Logger logger = Logger.getLogger(CyclicExecuteThread.class.getName());
    private AtomicInteger success;
    private CountDownLatch begin;
    private CountDownLatch end;
    private CyclicBarrier barrier;
    private int loop;

    public CyclicExecuteThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            begin.await();
            for (int i = 0; i < loop; i++) {
                //logger.log(Level.INFO, super.getName() + " is running " + i + "st...");
                try {
                    if (runTask()) {
                        success.incrementAndGet();
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, super.getName() + " thread exception occurs: ", e);
                } finally {
                    end.countDown();
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "{0} thread is interrupted!", super.getName());
            return;
        } catch (BrokenBarrierException e) {
            logger.log(Level.WARNING, "{0} thread is broken!", super.getName());
            return;
        }
    }

    protected abstract boolean runTask() throws Exception;

    public void setSuccess(AtomicInteger success) {
        this.success = success;
    }

    public void setBegin(CountDownLatch begin) {
        this.begin = begin;
    }

    public void setEnd(CountDownLatch end) {
        this.end = end;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }
}