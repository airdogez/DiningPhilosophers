package sample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.control.CheckBox;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Fork extends CheckBox{
    //Lock fork = new ReentrantLock();
    int pos;
    boolean picked = false;
    Philosopher owner;

    @Override
    public String toString() {
        return "fork-"+pos;
    }

    public boolean isPicked(Philosopher who) {

        return picked && who == owner;
    }

    public synchronized boolean pickUp(Philosopher who, String what) {
        //if(fork.tryLock()) {
        if(!picked && owner == null){
            owner = who;
            System.out.println(who + " picked up " + what + " " + this);
            picked = true;
            return true;
        }
        return false;
    }

    public void release(Philosopher who, String what){
        if (picked && owner == who){
            owner = null;
            picked = false;
            System.out.println(who + " released " + what + " " + this);
        }
    }
}
