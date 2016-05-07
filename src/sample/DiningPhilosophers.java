package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DiningPhilosophers extends Application{
    public static int MAX = 3;
    static String[] mNames = { "Aristoteles", "Platon", "Heraclito", "Pitagoras", "Nietzsche",
                        "Goethe", "Seneca", "Euclides", "Einstein", "Eco",
                        "Heidegger", "Hobbes", "Ivanov", "Kant", "Locke",
                        "Marx", "Moore", "Reid", "Schaeffer", "Timon"};
    static VBox root;
    static TextField numberText;
    static Button startButton;
    static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        */
        root = new VBox();
        numberText = new TextField();
        startButton = new Button();

        root.getChildren().add(startButton);
        root.getChildren().add(numberText);

        startButton.setText("Start Simulation");
        startButton.setOnAction(actionEvent -> new DiningPhilosophers().initialize());

        scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize(){
        int i;
        int N = Integer.parseInt(numberText.getText());
        Fork[] forks= new Fork[N];
        Thread[] mThreads = new Thread[N];
        for (i = 0; i < N; i++){
            forks[i] = new Fork();
            forks[i].pos = i;
            root.getChildren().add(new HBox(new Text("Tenedor" + (i+1)), forks[i]));
        }

        for (i = 0; i < N; i++) {
            int right = (i == N-1) ? 0 : i+1 ;
            Philosopher phill = new Philosopher(mNames[i], forks[i], forks[right]);

            root.getChildren().add(new HBox(new Text(mNames[i]), phill));
            mThreads[i] = new Thread(phill);
        }

        for (i = 0; i < N; i++){
            mThreads[i].start();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
