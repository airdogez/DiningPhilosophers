package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiningPhilosophers extends Application{
    public static int N = 5;
    Fork[] mForks = new Fork[N];
    Thread[] mThreads = new Thread[N];
    String[] mNames = {"Aristoteles", "Platon", "Heraclito", "Pitagoras", "Nietzsche"};


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
    }

    public void init(){
        int i = 0;
        for (i = 0; i < mForks.length; i++){
            mForks[i] = new Fork();
        }

        for (i = 0; i < mThreads.length; i++) {
            mThreads[i] = new Thread(new Philosopher(this, mNames[i] , i++));
        }

        for (Thread thread : mThreads){
            thread.start();
        }

        launch();
    }

    public static void main(String[] args) {
        new DiningPhilosophers().init();
    }
}
