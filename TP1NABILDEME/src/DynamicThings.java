public class DynamicThings extends AnimatedThings {
    // Vitesse de déplacement sur l'axe horizontal et vertical
    // J'utilise des double pour avoir des déplacements plus fluides
    private double speedX;
    private double speedY;

    public DynamicThings(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Par défaut, l'objet est immobile (vitesse = 0)
        this.speedX = 0;
        this.speedY = 0;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    // Méthode qui déplace l'objet selon sa vitesse
    // Je convertis les vitesses en int car les coordonnées x,y sont des entiers
    public void move() {
        this.x += (int) speedX;
        this.y += (int) speedY;
    }
}
