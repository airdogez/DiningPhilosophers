package sample;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher  implements  Runnable{

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Fork mLeftFork, mRightFork;
    private int mPosition;
    private DiningPhilosophers mContext;

    public Philosopher(DiningPhilosophers context, String name, int position){
        mContext = context;
        mName = name;
        mPosition = position;
        mState = State.THINKING;

        mRightFork = context.mForks[position];
        if(position == 0)
            mLeftFork = context.mForks[context.N - 1];
        else
            mLeftFork = context.mForks[position - 1];
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
            while (true){
                //Think: Sleep for a long while
                think();
                Thread.sleep(1000);
                //Take fork
                hungry();
                mLeftFork.take();
                Thread.sleep(500);
                mRightFork.take();
                Thread.sleep(500);
                //Eat
                eat();
                Thread.sleep(1000);
                //Drop fork
                mLeftFork.release();
                mRightFork.release();
                Thread.sleep(1000);

            }
        } catch (InterruptedException ie) {

        }
        mLeftFork.releaseIfMine();
        mRightFork.releaseIfMine();
        think();


    }

}
