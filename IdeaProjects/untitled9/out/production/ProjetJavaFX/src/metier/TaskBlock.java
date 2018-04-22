package metier;

import controller.ControllerJeu;
import javafx.concurrent.Task;

public class TaskBlock {
    static final int TEMPS_MOUVEMENT = 1000;

    private Block block;
    private ControllerJeu jeu;


    public TaskBlock(Block block, ControllerJeu jeu) {
        this.block = block;
        this.jeu = jeu;

        if (block.getClass().getCanonicalName().equals("metier.Ennemi"))
            ( new Thread(taskEnnemi) ).start();
        else
            ( new Thread(taskTir) ).start();
    }

    Task<Void> taskEnnemi = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            int vitesse = TEMPS_MOUVEMENT/block.getVitesse();
            while(true){
                Thread.sleep(vitesse);
                block.deplacer(Deplacer.BAS);
                if (block.getOrdonnee() >= 5){
                    jeu.removeBlock(block);
                    return null;
                }

                jeu.showBlock(block);
            }
        }
    };

    Task<Void> taskTir = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            int vitesse = TEMPS_MOUVEMENT/block.getVitesse();
            while(true){
                Thread.sleep(vitesse);
                block.deplacer(Deplacer.HAUT);
                if (block.getOrdonnee() < 0){
                    jeu.removeBlock(block);
                    return null;
                }

                jeu.showBlock(block);
            }
        }
    };
}
