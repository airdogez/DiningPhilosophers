package sample;

import java.util.Random;

import javafx.scene.control.CheckBox;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher extends CheckBox implements Runnable {

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Fork mLeftFork, mRightFork;

    public Philosopher(String name, Fork leftFork, Fork rightFork){
        super();
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
        Thread.sleep(500);
    }

    public void eat() throws InterruptedException {
        mState = State.EATING;
        System.out.println(mName + " is eating");
        setSelected(true);
        Thread.sleep(500);
    }

    public void hungry() throws InterruptedException{
        mState = State.HUNGRY;
        System.out.println(mName + " is hungry");
        Thread.sleep(500);
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
                think();
                hungry();
                mLeftFork.pickUp(this, "left");
                while (!mRightFork.pickUp(this, "right")){
                    mLeftFork.release(this, "left");
                    Thread.sleep(1000);
                    mLeftFork.pickUp(this, "left");
                }
                if(mLeftFork.isPicked(this) && mRightFork.isPicked(this)){
                    eat();
                    setSelected(false);
                    System.out.println(mName + " is full");
                    mLeftFork.release(this, "left");
                    mRightFork.release(this, "right");
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
