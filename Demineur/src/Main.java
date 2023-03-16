import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception{
      primaryStage.setTitle("Démineur by CARNOT Prisca");
      GridPane pane=new GridPane();
      GameScene theScene = new GameScene(pane, 600, 400,true);
      primaryStage.setScene(theScene);
      primaryStage.show();
   }


   public static void main(String[] args) {
      launch(args);
      }
}