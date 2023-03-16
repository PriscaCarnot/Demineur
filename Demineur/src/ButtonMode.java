import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ButtonMode {
    protected Button btn;

    public ButtonMode(GridPane grid,int row,int col, int translationx, int translationy,int height,int width){
        this.btn=new Button("");
        this.btn.setOpacity(0);
        this.btn.setPrefSize(width,height);
        this.btn.setTranslateX(translationx);
        this.btn.setTranslateY(translationy);
        btn.setOnMouseClicked(event->{
            if (col==0){

                GameScene.Height=7;
                GameScene.Width=7;
                GameScene.NbBomb=7;
                grid.setPadding(new Insets(70,30,50,195));
            }
            else if (col==1){

                GameScene.Height=10;
                GameScene.Width=10;
                GameScene.NbBomb=10;
                grid.setPadding(new Insets(45,30,50,140));
            }
            else if (col==2){

                GameScene.Height=12;
                GameScene.Width=18;
                GameScene.NbBomb=40;
                grid.setPadding(new Insets(12,30,50,12));
            }
            GameScene.init(grid);
        });
        grid.add(btn,row,col);
    }
}
