package sample;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Fork {
    Thread holder = null;

    public synchronized void take(int philosopher) throws  InterruptedException{
        while (holder!= null)
            wait();
        holder = Thread.currentThread();
    }

    public synchronized void release(int philosopher){
        holder = null;
        notifyAll();
    }
}
