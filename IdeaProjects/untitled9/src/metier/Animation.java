package metier;

import controller.ControllerJeu;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Animation {
    private static final String PATH_PLAYER = "/img/player.png";
    private static final String PATH_LINUX = "/img/linux.png";
    private static final String PATH_TIR = "/img/tir.png";
    private static final int DEBUT_FENETRE = 0;
    private static final int LARGEUR = 5;
    private static final int PDV = 10;
    private static final int TAILLE = 50;
    private static final int TEMPS_CREATION = 2000;
    private static final int VITESSE_ENNEMI = 1;
    private static final int VITESSE_TIR = 2;
    private static final int VITESSE_PLAYER = 1;

    private Player player;
    private ControllerJeu jeu;
    private Ennemi ennemi;
    private Tir tir;

    private Task<Void> creerEnnemi = new Task<>() {
        @Override public Void call() throws InterruptedException {
            Random random = new Random();
            while(true){
                ennemi = new Ennemi(TAILLE, VITESSE_ENNEMI, PDV, random.nextInt(LARGEUR), DEBUT_FENETRE, creerImage(PATH_LINUX));

                Platform.runLater(() -> {
                    jeu.addBlock(ennemi);
                });
                new TaskBlock(ennemi, jeu);

                Thread.sleep(TEMPS_CREATION);
            }
        }
    };

    public Animation(ControllerJeu jeu) {
        this.jeu = jeu;
        player = new Player(TAILLE, VITESSE_PLAYER, PDV, 2, 4, creerImage(PATH_PLAYER));
        jeu.addBlock(player);
    }

    private ImageView creerImage(String source){
        ImageView imageView = new ImageView(source);

        imageView.setFitHeight(TAILLE);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    public void commencer(){
        ( new Thread(creerEnnemi) ).start();
    }

    public void addTir(){
        tir = new Tir(TAILLE, VITESSE_TIR, PDV, player.getAbscisse(), player.getOrdonnee()-1, creerImage(PATH_TIR));
        new TaskBlock(tir, jeu);
        jeu.addBlock(tir);
    }

    public void movePlayer(Deplacer d){
        switch (d){
            case GAUCHE:
                if ( player.getAbscisse() > DEBUT_FENETRE )
                    player.deplacer(d);
                break;
            case DROITE:
                if ( player.getAbscisse() < LARGEUR-1 )
                    player.deplacer(d);
                break;
        }
        jeu.showBlock(player);
    }
}