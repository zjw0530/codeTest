package com.exe.entity;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ProcessReceiver {

    public static CountDownLatch latch;
}
