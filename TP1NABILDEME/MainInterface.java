import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame implements KeyListener {

    private Hero hero;
    private Dungeon dungeon;

    public MainInterface() {
        // --- 1. Initialisation des données ---
        TileManager tileManager = new TileManager(32, 32, "./tileSet.png");
        dungeon = new Dungeon(15, 20, tileManager);
        hero = Hero.getInstance();

        // On donne une image au héros
        hero.image = tileManager.getTile(0, 0);

        // On place le héros (position de départ)
        hero.x = 150;
        hero.y = 150;

        // --- CORRECTION HITBOX (Vital pour le déplacement) ---
        // On colle la HitBox sur le héros et on lui donne la bonne taille
        hero.getHitBox().setX(hero.x);
        hero.getHitBox().setY(hero.y);
        hero.getHitBox().setWidth(hero.image.getWidth(null));
        hero.getHitBox().setHeight(hero.image.getHeight(null));
        // -----------------------------------------------------

        // --- 2. Configuration de la fenêtre ---
        this.setTitle("Mon Super Jeu - TP2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        GameRender panel = new GameRender(dungeon, hero);
        this.getContentPane().add(panel);

        // Ajout du détecteur de clavier
        this.addKeyListener(this);

        this.setSize(new Dimension(20 * 32 + 20, 15 * 32 + 40));
        this.setVisible(true);

        // --- 3. Le Moteur du jeu (Timer) ---
        Timer timer = new Timer(50, e -> {
            hero.moveIfPossible(hero.getSpeedX(), hero.getSpeedY(), dungeon);
            panel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        new MainInterface();
    }

    // --- 4. Gestion des touches ---

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: hero.setSpeedX(5);  break;
            case KeyEvent.VK_LEFT:  hero.setSpeedX(-5); break;
            case KeyEvent.VK_UP:    hero.setSpeedY(-5); break;
            case KeyEvent.VK_DOWN:  hero.setSpeedY(5);  break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setSpeedX(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setSpeedY(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}