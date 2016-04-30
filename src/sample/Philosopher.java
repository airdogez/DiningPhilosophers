package sample;

import java.util.Random;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher implements Runnable{

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Fork mLeftFork, mRightFork;
    private Random mRandom = new Random();

    public Philosopher(String name, Fork leftFork, Fork rightFork){
        mName = name;
        mLeftFork = leftFork;
        mRightFork = rightFork;
        mState = State.THINKING;
    }

    @Override
    public String toString() {
        return mName;
    }

    public void think() throws InterruptedException{
        mState = State.THINKING;
        System.out.println(mName + " is thinking.");
        Thread.sleep(100);
    }

    public void eat() throws InterruptedException {
        mState = State.EATING;
        System.out.println(mName + " is eating");
        Thread.sleep(100);
    }

    public void hungry() throws InterruptedException{
        mState = State.HUNGRY;
        System.out.println(mName + " is hungry");
        Thread.sleep(100);
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


    //Thread.sleep simulates a costly action
    @Override
    public void run() {
        try {
            while (true) {
                //think();
                //hungry();
                if (mLeftFork.pickUp(this, "left")) {
                    if (mRightFork.pickUp(this, "right")){
                        eat();
                        //System.out.println(mName + " is full");
                        mRightFork.release(this, "right");
                    }
                    mLeftFork.release(this, "left");
                }
            }
        } catch (InterruptedException ie) {
            System.out.println(mName + " error");
            mLeftFork.release(this, "left");
            mRightFork.release(this, "right");
        } finally {
        }
    }

}
