package metier;

import javafx.scene.image.ImageView;

public class Tir extends Block {
    public Tir(int taille, int vitesse, int pdv, int abscisse, int ordonnee, ImageView imageView){
        super(taille, vitesse, pdv, abscisse, ordonnee, imageView);
    }

    @Override
    public void deplacer(Deplacer d) {
        if (d == Deplacer.HAUT)
            setOrdonnee(getOrdonnee()-1);
    }
}