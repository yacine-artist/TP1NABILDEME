public class Hitbox {
    private double x;
    private double y;
    private double width;
    private double height;

    public Hitbox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Méthode vitale pour détecter si deux objets se touchent
    public boolean intersect(Hitbox other) {
        // Vérifie si les rectangles se chevauchent
        if (this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y) {
            return true; // Ça touche !
        }
        return false;
    }

    // Getters et Setters (nécessaires pour que DynamicThings puisse bouger la HitBox)
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}