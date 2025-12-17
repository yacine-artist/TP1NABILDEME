import java.awt.Image;

public class DynamicThings extends AnimatedThings {
    private double speedX = 0;
    private double speedY = 0;

    // Le constructeur prend maintenant une Image !
    public DynamicThings(int x, int y, Image image) {
        super(x, y, image);
    }

    public void setSpeedX(double speedX) { this.speedX = speedX; }
    public void setSpeedY(double speedY) { this.speedY = speedY; }
    public double getSpeedX() { return speedX; }
    public double getSpeedY() { return speedY; }

    public void move() {
        this.x += (int) speedX;
        this.y += (int) speedY;
    }

    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {
        boolean isCollision = false;

        // Test de collision théorique
        this.getHitBox().setX(this.getHitBox().getX() + dx);
        this.getHitBox().setY(this.getHitBox().getY() + dy);

        if (dungeon != null) {
            for (Things thing : dungeon.getRenderList()) {
                if (thing instanceof SolidThings && thing != this) {
                    if (((SolidThings) thing).getHitBox().intersect(this.getHitBox())) {
                        isCollision = true;
                        break;
                    }
                }
            }
        }

        if (isCollision) {
            this.getHitBox().setX(this.getHitBox().getX() - dx);
            this.getHitBox().setY(this.getHitBox().getY() - dy);
        } else {
            this.x += dx;
            this.y += dy;
        }
    }
}