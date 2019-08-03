package com.lofthouse;

public class Consumer implements Runnable{

    private final Buffer syncBuffer;
    Thread t;
    public Consumer(Buffer b){
        syncBuffer = b;
        t = new Thread(this, "Consumer");
    }

    @Override
    public void run(){
        int i = 0;
        while(i++ < 10) System.out.println(("Consumed Value: " + syncBuffer.get()));
    }


}
