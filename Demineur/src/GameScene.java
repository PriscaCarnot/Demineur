import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class GameScene extends Scene {
    public static int Height;
    public static int Width;
    public static int NbBomb;
    public static ArrayList<Buttons> ListBtn;
    protected static ButtonMode easy,medium,hard;

    public GameScene(GridPane grid, double v, double v1, boolean b) {
        super(grid, v, v1, b);

        GameScene.ListBtn=new ArrayList<>();
        GameScene.accueil(grid);
    }

    public static void accueil(GridPane grid){
        new Backgd("bckgd.png",grid,0,0,600,400);
        Buttons.NbButtonsOn=0;
        grid.setPadding(new Insets(0,30,50,3));
        int tx=125;
        int ty=30;
        GameScene.easy=new ButtonMode(grid,0,0,-85+tx,270+ty,40,190);
        GameScene.medium=new ButtonMode(grid,0,1,70+tx,170+ty,40,200);
        GameScene.hard=new ButtonMode(grid,0,2,150+tx,190+ty,40,280);
    }

    public static void init(GridPane grid){
        grid.getChildren().remove(GameScene.easy.btn);
        grid.getChildren().remove(GameScene.hard.btn);
        grid.getChildren().remove(GameScene.medium.btn);
        new Backgd("background.jpg",grid,0,0,600,400);
        for (int i=0;i<Height;i++){
            for(int j=0;j<Width;j++) {
                ListBtn.add(new Buttons(grid, i,j));
            }
        }
/*
        int i=0;
        int x;
        int y;
        while(i<GameScene.NbBomb){
            x= ThreadLocalRandom.current().nextInt(0,Height);
            y=ThreadLocalRandom.current().nextInt(0,Width);
            if(ListBtn.get(x*Width+y).getBomb()==0) {
                ListBtn.get(x * Width + y).setBomb();
                ListBtn.get(x * Width + y).setChiffre(Width,Height,ListBtn,x,y);
                i++;
            }


        }


 */

    }
}
