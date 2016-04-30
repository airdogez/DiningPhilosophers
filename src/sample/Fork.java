package sample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Fork {
    Lock fork = new ReentrantLock();
    int pos;

    @Override
    public String toString() {
        return "fork-"+pos;
    }

    public synchronized boolean pickUp(Philosopher who, String what) throws  InterruptedException{
        if(fork.tryLock()) {
            //System.out.println(who + " picked up " + what + " " + this);
            return true;
        }
        return false;
    }

    public synchronized void release(Philosopher who, String what){
        //System.out.println(who + " released " + what + " " + this);
        fork.unlock();
    }
}
