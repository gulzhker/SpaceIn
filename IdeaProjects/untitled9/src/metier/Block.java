package metier;

import javafx.scene.image.ImageView;


public abstract class Block {
    private final int taille;
    private final int vitesse;
    private int pdv;
    private int abscisse;
    private int ordonnee;
    private ImageView imageView;

    public abstract void deplacer(Deplacer d);

    public Block(int taille, int vitesse, int pdv, int abscisse, int ordonnee, ImageView imageView){
        this.taille = taille;
        this.vitesse = vitesse;
        this.pdv = pdv;
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
        this.imageView = imageView;
    }

    public int getTaille() {
        return taille;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getPdv() {
        return pdv;
    }
    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public int getAbscisse() {
        return abscisse;
    }
    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }
    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
