import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Backgd {
    public Backgd(String fileName, GridPane group,int x,int y,int sizex,int sizey){
        Background img =  new Background(createImage(fileName,x,y,sizex,sizey));
        group.setBackground(img);
    }

    public Backgd(String fileName, Button btn){
        Background img =  new Background(createImage(fileName,0,0,35,35));
        btn.setBackground(img);
    }

    private static BackgroundImage createImage(String url, int x, int y,int sizex,int sizey) {
        return new BackgroundImage(
            new Image(url),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.LEFT, x, false, Side.TOP, y, false),
            new BackgroundSize(sizex, sizey, false, false, false, false));
    }
}
