package metier;

import javafx.scene.image.ImageView;

public class Player extends Block {
    public Player(int taille, int vitesse, int pdv, int abscisse, int ordonnee, ImageView imageView){
        super(taille, vitesse, pdv, abscisse, ordonnee, imageView);
    }

    @Override
    public void deplacer(Deplacer d) {
        switch (d){
            case DROITE:
                setAbscisse(getAbscisse()+1);
                break;
            case GAUCHE :
                setAbscisse(getAbscisse()-1);
                break;
        }
    }
}