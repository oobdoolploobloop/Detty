package com.lofthouse;

public class Buffer {
    private int num = -1;
    private Boolean state = false;
    public synchronized void set(int n){
        try{
            while(state){
                wait();
            }
        } catch(InterruptedException ix){
            ix.printStackTrace();
        }
        num = n;
        state = true;
        notify();
    }

    public synchronized int get(){
        try{
            while(!state){
                wait();
            }
        }catch(InterruptedException ix){
            ix.printStackTrace();
        }
        state = false;
        System.out.println("Item " + num);
        notify();
        return num;
    }
}
