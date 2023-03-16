import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


public class Buttons {
    static int NbButtonsOn=0;
    protected int NbBtnFin=GameScene.Height*GameScene.Width-GameScene.NbBomb;
    protected int x;
    protected int y;

    protected int State; // 1 découverte 0 cachée
    protected int Bomb; // 1 bombe 0 vide
    protected int Chiffre; // des cases à côté des bombes
    protected Button btn;

    public Buttons(GridPane grid, int x, int y){
        this.btn=new Button("");
        this.btn.setMinSize(32,31);
        this.btn.setMaxSize(31,31);
        this.btn.setTextFill(Color.BLACK); // couleur du texte
        this.btn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));// changer la taille de l'écriture
        this.Chiffre=0;
        this.x=x;
        this.y=y;
        if((this.x+this.y)%2==0){
            new Backgd("blanc.png",this.btn);
        }
        else{
            new Backgd("noir.jpg",this.btn);
        }
        this.Bomb=0;
        btn.setOnMouseClicked(event->{
            if(Buttons.NbButtonsOn==0){
                int i=0;
                int clicx;
                int clicy;
                while(i<GameScene.NbBomb){
                    clicx= ThreadLocalRandom.current().nextInt(0,GameScene.Height);
                    clicy=ThreadLocalRandom.current().nextInt(0,GameScene.Width);
                    if((GameScene.ListBtn.get(clicx*GameScene.Width+clicy).getBomb()==0) && (this.setBombs(clicx,clicy)==0)){
                        GameScene.ListBtn.get(clicx * GameScene.Width + clicy).setBomb();
                        GameScene.ListBtn.get(clicx * GameScene.Width + clicy).setChiffre(GameScene.Width,GameScene.Height,GameScene.ListBtn,clicx,clicy);
                        i++;
                    }
                }
            }
            if(this.State==0) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    this.reveal(grid,GameScene.Width,GameScene.Height,GameScene.ListBtn);
                    if (Buttons.NbButtonsOn==this.NbBtnFin){
                        System.out.println("Gagné!");
                        this.btn.setText("");
                        new Backgd("trophee.png",this.btn);
                        setRestart(grid);
                    }
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.btn.setText("X");
                    this.btn.setTextFill(Color.RED);
                    this.State=2;
                }
            }
            else if ((this.State==2) && (event.getButton() == MouseButton.SECONDARY)){
                this.btn.setText("");
                this.btn.setTextFill(Color.BLACK);
                this.State=0;
            }
        });

        grid.add(btn,y,x);
        GridPane.getHgrow(btn);
    }

    public void setBomb(){
        this.Bomb=1;
    }

    public int getBomb(){
        return this.Bomb;
    }
    //Affecte le nombre de bombe autour d'une case
    public void setChiffre( int Width,int Height,ArrayList<Buttons> L,int x, int y){
        if(x!=0){
            if(y!=0) {
                L.get((x - 1) * Width + y - 1).Chiffre++;
            }
            L.get((x-1)*Width+y).Chiffre++;
            if(y!=Width-1) {
                L.get((x - 1) * Width + y + 1).Chiffre++;
            }
        }

        if(x!=Height-1) {
            if(y!=0) {
                L.get((x + 1) * Width + y - 1).Chiffre++;
            }
            L.get((x + 1) * Width + y).Chiffre++;
            if(y!=Width-1) {
                L.get((x + 1) * Width + y + 1).Chiffre++;
            }
        }
        if(y!=0) {
            L.get(x * Width + y - 1).Chiffre++;
        }
        if(y!=Width-1) {
            L.get(x * Width + y + 1).Chiffre++;
        }
    }
//Révèle si la case est vide sans bombe autour, vide avec une bombe autour, ou piégée
    public void reveal (GridPane grid,int Width,int Height,ArrayList<Buttons> L){
        if(this.State==1 || this.State==2){

        }
        else if(this.getBomb()==1){
            System.out.println("perdu!");

            this.btn.setText("");
            new Backgd("bombe.png",this.btn);
            setRestart(grid);



        }
        else if (this.Chiffre!=0){
            this.btn.setText(Integer.toString(this.Chiffre));
            this.setColor();
            new Backgd("case.PNG",this.btn);
            this.State=1;
            Buttons.NbButtonsOn++;
        }
        else{
            Buttons.NbButtonsOn++;
            this.btn.setText("");
            new Backgd("case.PNG",this.btn);
            this.State=1;
            if(this.x!=0){
                if(y!=0) {
                    L.get((x - 1) * Width + y - 1).reveal(grid,Width,Height,L);
                }
                L.get((this.x-1)*Width+this.y).reveal(grid,Width,Height,L);
                if(y!=Width-1) {
                    L.get((x - 1) * Width + y + 1).reveal(grid,Width,Height,L);
                }
            }
            if(this.x!=Height-1) {
                if(y!=0) {
                    L.get((x + 1) * Width + y - 1).reveal(grid,Width,Height,L);
                }
                L.get((this.x + 1) * Width + this.y).reveal(grid,Width,Height,L);
                if(y!=Width-1) {
                    L.get((x + 1) * Width + y + 1).reveal(grid,Width,Height,L);
                }
            }
            if(this.y!=0) {
                L.get(this.x * Width + this.y - 1).reveal(grid,Width,Height,L);
            }
            if(this.y!=Width-1) {
                L.get(this.x * Width + this.y + 1).reveal(grid,Width,Height,L);
            }
        }
    }

    public void setColor() {
        switch (this.Chiffre) {
            case 1 -> this.btn.setTextFill(Color.BLUE);
            case 2 -> this.btn.setTextFill(Color.GREEN);
            case 3 -> this.btn.setTextFill(Color.RED);
            case 4 -> this.btn.setTextFill(Color.VIOLET);
            case 5 -> this.btn.setTextFill(Color.ORANGE);
            case 6 -> this.btn.setTextFill(Color.BLUEVIOLET);
            case 7 -> this.btn.setTextFill(Color.DARKGREEN);
            case 8 -> this.btn.setTextFill(Color.GOLD);
        }
    }

    //Attend 5 secondes avant de redémarer le jeu
    public void setRestart(GridPane grid){
        Timer time=new Timer();
        time.schedule(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(()-> {

                    for(int i=0;i<GameScene.Width*GameScene.Height;i++){
                        grid.getChildren().remove(GameScene.ListBtn.get(i).btn);
                    }
                    GameScene.ListBtn.clear();
                    GameScene.accueil(grid);
                    time.cancel();
                        }
                );
            }
        },5000);
    }
//Pour le premier clic on ne clique pas sur une case piégée avec des cases autour non piégée également
    public int setBombs(int x, int y) {
        int refx = this.x;
        int refy = this.y;

        if (Math.abs(refx-x)<3 && Math.abs(refy-y)<3){
            return 1;
        }
        return 0;

    }
}
