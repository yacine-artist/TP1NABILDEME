public class TileManager {
    // Dimensions d'une tuile (case) du donjon en pixels
    // final car ces valeurs ne changeront jamais pendant l'exécution
    private final int tileHeight;
    private final int tileWidth;

    public TileManager(int tileHeight, int tileWidth) {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
    }

    // Getters pour convertir entre coordonnées de tuiles et pixels
    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
