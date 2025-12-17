import java.awt.Image;

public class SolidThings extends Things {
    // La boite de collision
    private Hitbox hitBox;

    public SolidThings(int x, int y, Image image) {
        super(x, y, image); // On construit le père
        // On crée la hitbox avec la même taille que l'image
        this.hitBox = new Hitbox(x, y, width, height);
    }

    public Hitbox getHitBox() {
        return hitBox;
    }
}