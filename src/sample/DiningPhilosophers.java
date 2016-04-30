package sample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DiningPhilosophers extends Application{
    public static int N = 5;
    Fork[] mForks = new Fork[N];
    //Lock forks[] = new ReentrantLock[N];
    Philosopher[] mPhilosophers = new Philosopher[N];
    String[] mNames = {"Aristoteles", "Platon", "Heraclito", "Pitagoras", "Nietzsche"};


    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        */
        Button startButton = new Button();
        startButton.setText("Start Simulation");
        startButton.setOnAction((EventHandler<ActionEvent>) actionEvent -> new DiningPhilosophers().initialize());
        StackPane root =  new StackPane();
        root.getChildren().add(startButton);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize(){
        int i;
        for (i = 0; i < 5; i++){
            mForks[i] = new Fork();
            //forks[i] = new ReentrantLock();
        }

        for (i = 0; i < 5; i++) {
            mPhilosophers[i] = new Philosopher(this, mNames[i] , i);
        }

        for (i = 0; i < 5; i++){
            mPhilosophers[i].thread.start();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
