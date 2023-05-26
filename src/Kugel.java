import GLOOP.*;
public class Kugel {
    private GLKugel kugel;
    private Box dieBox;
    private Spielfeld feld;

    private double radius;
    private boolean istAktiv = true;
    private double a, b, d, f, g, rand1;
    private double speed;
    private int index, score;
    Kugel[] kugeln;

    public Kugel(int pRadius, Spielfeld pFeld, Box pBox, double pspeed, int pindex) {
        speed = pspeed;
        index = pindex;

        if (Math.random() < 0.5) {
            a = 1;
            f = 1;
        } else {
            a = -1;
            f = -1;
        }
        feld = pFeld;
        dieBox = pBox;
        kugel = new GLKugel((Math.random() * 400 * a) - 100, 40, Math.random() * 400 * f, pRadius);
        kugel.setzeFarbe(Math.random(), Math.random(), Math.random());
        radius = pRadius;
    }

    public void istGetroffen() {
        g = (kugel.gibX() - dieBox.gibX());

        b = (kugel.gibZ() - dieBox.gibZ());
        d = Math.sqrt(g * g + b * b);
        if (d <= radius + 28) {
            score++;
            kugel.setzePosition(99999, 99999, 99999);
            istAktiv = false;
        }
    }

    public void istKugelGetroffen() {
        for (int i = 0; i < kugeln.length; i++) {
            g = (kugeln[i].gibX() - kugel.gibX());

            b = (kugeln[i].gibZ() - kugel.gibZ());
            d = Math.sqrt(g * g + b * b);
            if (i != index) {
                if (d <= radius * 2 - 10) {
                    a = a * -0.9;
                    f = f * -1;
                }
            }
        }
    }

    public void respawn(double pPos) {
        kugel.setzePosition(230 - pPos, 480, 500);
        kugel.setzeSelbstleuchten(1, 1, 1);
        kugel.skaliere(0.2, 0.2, 0.2);
        f = 0;
        a = 0;
        rand1 = 150;
    }

    public void startSpawn() {

        for (int i = 0; i < kugeln.length; i++) {
            g = (kugeln[i].gibX() - kugel.gibX());

            b = (kugeln[i].gibZ() - kugel.gibZ());
            d = Math.sqrt(g * g + b * b);
            if (d <= radius * 2) {
                kugeln[i].setzePosition((Math.random() * 400 * a) - 100, 40, Math.random() * 500 * f);
            }


        }
    }


    public void bewegeHorizontal() {
        if (istAktiv) {
            if (kugel.gibX() <= feld.getBreite() / 2 && kugel.gibX() >= feld.getBreite() / -2) {
                kugel.verschiebe(a * speed, 0, 0);

            } else {
                a = -a;
                kugel.verschiebe(8 * a, 0, 0);
            }

        }
    }

    public void bewegeVertikal() {
        if (istAktiv) {

            if (kugel.gibZ() <= feld.getTiefe() / 2 + rand1 * -1 && kugel.gibZ() >= feld.getTiefe() / -2 + rand1 * 1) {
                kugel.verschiebe(0, 0, f * speed);
            } else {
                f = -f;
                kugel.verschiebe(0, 0, radius * f * 3);

            }

        }

    }

    public int gibScore(){
        return score;
    }
    public void setzeScore(int pScore){
        score=pScore;
    }
    public double gibX() {
        return  kugel.gibX();
    }
    public double gibZ() {
        return  kugel.gibZ();
    }
    public void uebergebeKugeln(Kugel[] pKugel){
        kugeln = pKugel;
    }
    public void setzePosition(double px,double py, double pz){
        kugel.setzePosition(px,py,pz);
    }
    public void setzeSichtbarkeit(boolean ptrue){
        kugel.setzeSichtbarkeit(ptrue);
    }
}

