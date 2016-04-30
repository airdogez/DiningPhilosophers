package sample;

/**
 * Created by aqws3 on 4/29/16.
 */
public class Philosopher implements Runnable{

    enum State {THINKING, HUNGRY, EATING}

    private String mName;
    private State mState;
    private Fork mLeftFork, mRightFork;
    private int mPosition;
    private DiningPhilosophers mContext;
    Thread thread;

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
                mLeftFork.take();
                System.out.println(mName + " takes left fork");
                Thread.sleep(500);
                mRightFork.take();
                System.out.println(mName + " takes right fork");
                Thread.sleep(500);
                //Eat
                eat();
                System.out.println(mName + " is eating");
                Thread.sleep(1000);
                //Drop fork
                System.out.println(mName + " is full");
                mLeftFork.release();
                System.out.println(mName + " releases left fork");
                mRightFork.release();
                System.out.println(mName + " releases right fork");
                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {

        }
        mLeftFork.releaseIfMine();
        mRightFork.releaseIfMine();
        thread = new Thread(this);

    }

}
