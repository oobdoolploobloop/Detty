package com.lofthouse;

public class Phone extends Thread{
    public int threadNum;
    public boolean talking = false;

    public Phone(int threadNum){
        this.threadNum = threadNum;
    }

    public void run(){
        System.out.println("Thread " + threadNum + " reporting in");
        if(threadNum == 1){
            Broadcast("Message from Thread "+ threadNum);
        }else{
            Listen();
        }
    }

    public synchronized void Broadcast(String msg){
        if(!talking){
            System.out.println("Phone " + threadNum + " isn't talking, waiting to talk");
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        talking = true;
        System.out.println("Phone " + threadNum + " is now talking");
        System.out.println("Broadcast message: " + msg);
        notify();

    }

    public synchronized void Listen(){

    }
}
