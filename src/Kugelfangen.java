import GLOOP.*;
public class Kugelfangen {
    private GLEntwicklerkamera kamera;
    private GLLicht licht;
    private GLHimmel himmel;
    private GLTastatur tastatur;
    GLTafel tafel;
    private Spielfeld spielfeld;
    private Box dieBox;
    private Kugel [] kugel;
    private double speed,a,f;
    int score = 0;

    public Kugelfangen() {
        kamera = new GLEntwicklerkamera();
        kamera.setzePosition(0, 500, 800);
        spielfeld = new Spielfeld(1000, 1000);
        licht = new GLLicht();
        himmel = new GLHimmel("src/img/Grass.jpg");
        tastatur = new GLTastatur();
        kugel = new Kugel [10];
        dieBox= new Box();
        speed= Math.random();
        tafel = new GLTafel(0,40,-550,200,100);
        tafel.setzeText("Score: "+score,60);
        for(int i = 0; i< kugel.length;i++){
            kugel[i] = new Kugel(20,spielfeld,dieBox, 5,i);
        }
        for(int i = 0; i< kugel.length;i++){
            kugel[i].uebergebeKugeln(kugel);
        }
        if(Math.random()<0.5){
            a=1;
            f=1;
        }else{
            a=-1;
            f=-1;
        }




        fuehreAus();
    }
    public void updateScore(){
        for (int i = 0; i < kugel.length; i++) {
            score += kugel[i].gibScore();
            kugel[i].setzeScore(0);
            tafel.setzeText("Score: "+score,60);
        }
    }
    public void fuehreAus() {

        for (int i = 0; i < kugel.length; i++) {
            kugel[i].startSpawn();
            kugel[i].setzeSichtbarkeit(true);
        }
        while (!tastatur.esc()) {
            Sys.warte(10);
            updateScore();
            for (int i = 0; i < kugel.length; i++) {
                kugel[i].istKugelGetroffen();
                kugel[i].istGetroffen();
                kugel[i].bewegeHorizontal();
                kugel[i].bewegeVertikal();


            }
            if(tastatur.links()){
                dieBox.bewegeLinks();
            }
            if(tastatur.rechts()){
                dieBox.bewegeRechts();
            }
            if(tastatur.oben()){
                dieBox.bewegeOben();
            }
            if(tastatur.unten()){
                dieBox.bewegeUnten();
            }

        }
    }
}