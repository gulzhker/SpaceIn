package metier;

import javafx.scene.image.ImageView;

public class Ennemi extends Block{
    public Ennemi(int taille, int vitesse, int pdv, int abscisse, int ordonnee, ImageView imageView){
        super(taille, vitesse, pdv, abscisse, ordonnee, imageView);
    }

    @Override
    public void deplacer(Deplacer d) {
        if (d == Deplacer.BAS)
            setOrdonnee(getOrdonnee()+1);
    }
}