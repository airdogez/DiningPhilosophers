package sample;

import java.util.concurrent.locks.Lock;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher implements Runnable{

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Lock mLeftFork, mRightFork;
    private int mPosition;
    private DiningPhilosophers mContext;
    Thread thread;

    public Philosopher(DiningPhilosophers context, String name, int position){
        mContext = context;
        mName = name;
        mPosition = position;
        mState = State.THINKING;

        mRightFork = context.forks[position];
        if(position == 0)
            mLeftFork = context.forks[context.N - 1];
        else
            mLeftFork = context.forks[position - 1];

        thread = new Thread(this);
    }


    public void think() {
        mState = State.THINKING;
    }

    public void eat() {
        mState = State.EATING;
    }

    public void hungry() {
        mState = State.HUNGRY;
    }

    public boolean isHungry() {
        return mState == State.HUNGRY;
    }

    public boolean isEating() {
        return mState == State.EATING;
    }

    public boolean isThinking() {
        return mState == State.THINKING;
    }


    //Threa.sleep simulates a costly action
    @Override
    public void run() {
        try {
            while (true) {
                //Think: Sleep for a long while
                think();
                System.out.println(mName + " is thiking");
                Thread.sleep(1000);
                //Take fork
                hungry();
                System.out.println(mName + " is hungry");
                //mLeftFork.tryLock(1000, TimeUnit.MILLISECONDS);
                mLeftFork.lock();
                System.out.println(mName + " takes left fork");
                Thread.sleep(500);
                //mRightFork.tryLock(1000, TimeUnit.MILLISECONDS);
                mLeftFork.lock();
                System.out.println(mName + " takes right fork");
                Thread.sleep(500);
                //Eat
                eat();
                System.out.println(mName + " is eating");
                Thread.sleep(1000);
                //Drop fork
                System.out.println(mName + " is full");
                if(mLeftFork.tryLock())
                    mLeftFork.unlock();
                System.out.println(mName + " releases left fork");
                if(mRightFork.tryLock())
                    mRightFork.unlock();
                System.out.println(mName + " releases right fork");
                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {
            System.out.println(mName + " error");
        }
        mLeftFork.unlock();
        mRightFork.unlock();
        thread = new Thread(this);

    }

}
