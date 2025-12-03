import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileManager {
    private Image[][] tiles;
    private int width;
    private int height;

    public TileManager(int width, int height, String fileName) {
        this.width = width;
        this.height = height;
        setTiles(width, height, fileName);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void setTiles(int width, int height, String fileName) {
        try {
            // Charge le fichier image
            BufferedImage tileSheet = ImageIO.read(new File(fileName));

            // Calcule le nombre de tuiles (colonnes et lignes)
            int numTilesX = tileSheet.getWidth() / width;
            int numTilesY = tileSheet.getHeight() / height;
            tiles = new Image[numTilesX][numTilesY];

            // Découpe l'image en petits morceaux
            for (int x = 0; x < numTilesX; x++) {
                for (int y = 0; y < numTilesY; y++) {
                    tiles[x][y] = tileSheet.getSubimage(x * width, y * height, width, height);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur de chargement de l'image : " + fileName);
            e.printStackTrace();
        }
    }

    public Image getTile(int x, int y) {
        // Sécurité pour ne pas planter si l'image n'est pas chargée
        if (tiles != null && x < tiles.length && y < tiles[0].length) {
            return tiles[x][y];
        }
        return null;
    }
}