package sample;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher  implements  Runnable{

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Fork mLeftFork, mRightFork;

    public Philosopher(String name){
        mName = name;
        mState = State.THINKING;
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

    @Override
    public void run() {

    }

}
