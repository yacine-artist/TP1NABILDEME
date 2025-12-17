import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame implements KeyListener {

    private Hero hero;
    private Dungeon dungeon;

    public MainInterface() {

        // 1) Décor : tileset 32x32
        TileManager tileManager = new TileManager(32, 32, "./tileset.png");

        // 2) TP3 : map depuis fichier
        dungeon = new Dungeon("./level1.txt", tileManager);

        // 3) Héros : on prend un sprite depuis hero.png (spritesheet)
        hero = Hero.getInstance();
        hero.image = loadHeroSpriteFromSheet("./hero.png", 0, 0); // (tileX, tileY)

        // 4) Position de départ
        hero.x = 150;
        hero.y = 150;

        // 5) Hitbox = taille du sprite
        hero.getHitBox().setX(hero.x);
        hero.getHitBox().setY(hero.y);
        hero.getHitBox().setWidth(hero.image.getWidth(null));
        hero.getHitBox().setHeight(hero.image.getHeight(null));

        // 6) Fenêtre
        this.setTitle("Mon Super Jeu - TP3");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        GameRender panel = new GameRender(dungeon, hero);
        this.getContentPane().add(panel);

        this.addKeyListener(this);

        this.setSize(new Dimension(
                dungeon.getWidth() * 32 + 20,
                dungeon.getHeight() * 32 + 40
        ));
        this.setVisible(true);

        // 7) Boucle de jeu
        Timer timer = new Timer(50, e -> {
            hero.moveIfPossible(hero.getSpeedX(), hero.getSpeedY(), dungeon);

            // IMPORTANT : hitbox collée au héros en permanence
            hero.getHitBox().setX(hero.x);
            hero.getHitBox().setY(hero.y);

            panel.repaint();
        });
        timer.start();
    }

    // Charge la spritesheet hero.png, enlève le rose, découpe un sprite 32x32
    private BufferedImage loadHeroSpriteFromSheet(String filePath, int tileX, int tileY) {
        try {
            BufferedImage sheet = ImageIO.read(new File(filePath));
            sheet = makeMagentaTransparent(sheet);

            int px = tileX * 32;
            int py = tileY * 32;

            BufferedImage sprite = sheet.getSubimage(px, py, 32, 32);
            System.out.println("HERO SPRITE = hero.png tile (" + tileX + "," + tileY + ")");
            return sprite;

        } catch (Exception e) {
            System.out.println("⚠️ Impossible de charger/découper hero.png : " + e.getMessage());
            // fallback : petit carré visible (évite null)
            return new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }
    }

    // Rend le fond rose (#FF00FF) transparent
    private BufferedImage makeMagentaTransparent(BufferedImage src) {
        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        int magenta = 0xFFFF00FF; // ARGB

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = src.getRGB(x, y);
                if (rgb == magenta) out.setRGB(x, y, 0x00000000);
                else out.setRGB(x, y, rgb);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        new MainInterface();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: hero.setSpeedX(5);  break;
            case KeyEvent.VK_LEFT:  hero.setSpeedX(-5); break;
            case KeyEvent.VK_UP:    hero.setSpeedY(-5); break;
            case KeyEvent.VK_DOWN:  hero.setSpeedY(5);  break;

            // BONUS : changer rapidement de perso pour trouver un bon sprite
            case KeyEvent.VK_1: hero.image = loadHeroSpriteFromSheet("./hero.png", 0, 0); break;
            case KeyEvent.VK_2: hero.image = loadHeroSpriteFromSheet("./hero.png", 1, 0); break;
            case KeyEvent.VK_3: hero.image = loadHeroSpriteFromSheet("./hero.png", 2, 0); break;
            case KeyEvent.VK_4: hero.image = loadHeroSpriteFromSheet("./hero.png", 3, 0); break;
        }

        // si on change d'image, on recale la hitbox
        hero.getHitBox().setWidth(hero.image.getWidth(null));
        hero.getHitBox().setHeight(hero.image.getHeight(null));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) hero.setSpeedX(0);
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) hero.setSpeedY(0);
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
