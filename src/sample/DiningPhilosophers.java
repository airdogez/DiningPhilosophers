package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DiningPhilosophers extends Application{
    public static int N = 5;
    //Fork[] mForks = new Fork[N];
    Fork forks[] = new Fork[N];
    Thread[] mThreads = new Thread[N];
    String[] mNames = { "Aristoteles", "Platon", "Heraclito", "Pitagoras", "Nietzsche",
                        "Goethe", "Seneca", "Euclides", "Einstein", "Eco",
                        "Heidegger", "Hobbes", "Ivanov", "Kant", "Locke",
                        "Marx", "Moore", "Reid", "Schaeffer", "Timon"};


    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        */
        Button startButton = new Button();
        startButton.setText("Start Simulation");
        startButton.setOnAction(actionEvent -> new DiningPhilosophers().initialize());
        StackPane root =  new StackPane();
        root.getChildren().add(startButton);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize(){
        int i;
        for (i = 0; i < N; i++){
            forks[i] = new Fork();
            forks[i].pos = i;
        }

        for (i = 0; i < N; i++) {
            int right = (i == N-1) ? 0 : i+1 ;
            mThreads[i] = new Thread(new Philosopher(mNames[i], forks[i], forks[right]));
        }

        for (i = 0; i < N; i++){
            mThreads[i].start();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
