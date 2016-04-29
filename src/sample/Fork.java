package sample;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Fork {
    Thread holder = null;

    public synchronized void take() throws  InterruptedException{
        while (holder!= null)
            wait();
        holder = Thread.currentThread();
    }

    public synchronized void release(){
        holder = null;
        notifyAll();
    }

    public synchronized void releaseIfMine(){
        if (holder == Thread.currentThread())
            holder = null;
        notifyAll();
    }
}
