import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener; // Pour l'animation plus tard si besoin

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;

    // Le constructeur reçoit le donjon et le héros pour savoir quoi dessiner
    public GameRender(Dungeon dungeon, Hero hero) {
        this.dungeon = dungeon;
        this.hero = hero;
    }

    // C'est ici que la magie opère : on dessine tout !
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Nettoie l'écran

        // 1. On dessine tous les objets du donjon (murs et sol) [cite: 207]
        // Utilise la renderList qu'on vient de créer
        for (Things thing : dungeon.getRenderList()) {
            thing.draw(g);
        }

        // 2. On dessine le héros par-dessus
        hero.draw(g);
    }
}