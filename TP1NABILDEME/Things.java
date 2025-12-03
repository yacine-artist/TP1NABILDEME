import java.awt.Graphics;
import java.awt.Image;

public class Things {
    // Position et taille
    public int x, y;
    public int width, height;
    // L'image de l'objet
    public Image image;

    // Le constructeur (la notice de montage de l'objet)
    public Things(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        // Si il y a une image, on prend sa taille, sinon on met 0
        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
    }

    // Une méthode pour se dessiner (on s'en servira plus tard)
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        }
    }
}