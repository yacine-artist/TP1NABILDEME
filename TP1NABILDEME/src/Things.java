public class Things {
    // Attributs protected pour que les classes enfants puissent y accéder directement
    // Position et dimensions de l'objet dans le jeu
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Things(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
