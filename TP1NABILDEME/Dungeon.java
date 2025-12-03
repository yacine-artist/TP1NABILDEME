import java.util.ArrayList;

public class Dungeon {
    // Liste unique pour l'affichage graphique (remplace thingsList)
    private ArrayList<Things> renderList = new ArrayList<>();

    private char[][] map;
    private int height;
    private int width;
    private TileManager tileManager;

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.map = new char[width][height]; // Attention: width d'abord pour x, height pour y

        // 1. On dessine le plan (W pour mur, espace pour vide)
        initializeMap();

        // 2. On crée les objets graphiques avec leurs images
        respawnListOfThings();
    }

    // Remplit le tableau de caractères map[][]
    private void initializeMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Murs extérieurs
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    map[x][y] = 'W';
                }
                // AJOUT : Un obstacle au milieu pour tester la collision !
                else if (x == 10 && y > 5 && y < 10) {
                    map[x][y] = 'W';
                }
                // Sinon, espace vide
                else {
                    map[x][y] = ' ';
                }
            }
        }
    }

    // C'est la méthode importante du TP2 : elle crée les objets avec les IMAGES
    public void respawnListOfThings() {
        renderList.clear();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = map[x][y];
                // Calcul de la position en pixels à l'écran
                int pixelX = x * tileManager.getWidth();
                int pixelY = y * tileManager.getHeight();

                if (c == 'W') {
                    // C'est un MUR : on crée un SolidThings
                    // On donne l'image (0,0) du tileset (le mur)
                    renderList.add(new SolidThings(pixelX, pixelY, tileManager.getTile(0, 0)));
                } else {
                    // C'est du SOL : on crée un Things simple
                    // On donne l'image (1,0) du tileset (le sol, par exemple)
                    renderList.add(new Things(pixelX, pixelY, tileManager.getTile(1, 0)));
                }
            }
        }
    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    public int getHeight() { return height; }
    public int getWidth() { return width; }
}