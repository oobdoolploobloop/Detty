package com.lofthouse;

import java.util.Random;

public class Producer implements Runnable{
    private final Buffer syncBuffer;
    Thread t;
    private final Random r = new Random();

    public Producer(Buffer b){
        syncBuffer = b;
        t = new Thread(this, "Producer");
    }
    @Override
    public void run(){
        int i = 0;
        while(i < 10){
            int val = r.nextInt();
            syncBuffer.set(val);
            System.out.println("Produced val " + val);
        }
    }
}
