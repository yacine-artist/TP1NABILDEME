public class SolidThings extends Things {
    // Une hitbox pour gérer les collisions
    // Seuls les objets solides (murs, obstacles) ont besoin d'une hitbox
    private Hitbox hitbox;

    public SolidThings(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Je crée une hitbox avec les mêmes dimensions que l'objet
        this.hitbox = new Hitbox(x, y, width, height);
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    // Méthode pour synchroniser la hitbox avec la position de l'objet
    // Utile si l'objet se déplace, sa hitbox doit suivre
    public void updateHitbox() {
        hitbox.setX(this.x);
        hitbox.setY(this.y);
    }
}
