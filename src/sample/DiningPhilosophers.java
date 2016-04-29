package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiningPhilosophers extends Application{
    public static int N = 5;
    private final Fork[] mForks = new Fork[N];

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
    }

    public void init(){
        for (Fork fork : mForks){
            fork = new Fork();
        }
    }

    public static void main(String[] args) {
        //launch(args);
        Thread t1 = new Thread(new Philosopher("Thread 1"));
        Thread t2 = new Thread(new Philosopher("Thread 2"));
        Thread t3 = new Thread(new Philosopher("Thread 3"));
        Thread t4 = new Thread(new Philosopher("Thread 4"));
        Thread t5 = new Thread(new Philosopher("Thread 5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
