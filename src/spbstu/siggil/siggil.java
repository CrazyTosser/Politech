package spbstu.siggil;

import javafx.application.Application;
<<<<<<< Updated upstream
import javafx.stage.Stage;

public class siggil extends Application {

=======
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class siggil extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;

>>>>>>> Stashed changes
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
<<<<<<< Updated upstream

=======
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Сиггил");

        initSingleMode();
    }

    public void initSingleMode() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(siggil.class.getResource("view/single.fxml"));
            rootLayout = (BorderPane) loader.load();
            ImageView img = new ImageView(new Image(siggil.class.getResourceAsStream("resourse/tyz.png")));
            rootLayout.setCenter(img);
            Scene sc = new Scene(rootLayout);
            primaryStage.setScene(sc);
            primaryStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
>>>>>>> Stashed changes
    }
}
