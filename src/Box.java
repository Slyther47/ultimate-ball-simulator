import GLOOP.*;
public class Box {
    private GLQuader quader;
    private Spielfeld feld;
    private double vX, vZ;
    private double breite, tiefe;

    public Box() {
        quader = new GLQuader(-40, 0, -200, 100, 25, 80);
        quader.setzeTextur("src/img/veneno left.jpg");
    }




    public void bewegeLinks() {
        if (quader.gibX() > -455) {
            quader.verschiebe(-3, 0, 0);
        }
    }
    public void bewegeRechts() {
        if (quader.gibX()<455) {
            quader.verschiebe(3,0,0);
        }}
    public void bewegeUnten() {
        if (quader.gibZ()<455) {
            quader.verschiebe(0,0,3);
        }}
    public void bewegeOben() {
        if (quader.gibZ() > -455) {
            quader.verschiebe(0,0,-3);
        }}
    public double gibX() {
        return  quader.gibX();
    }
    public double gibZ() {
        return quader.gibZ();
    }
}

