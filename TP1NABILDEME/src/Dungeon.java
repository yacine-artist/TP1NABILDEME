import java.util.ArrayList;

public class Dungeon {
    // Carte du donjon : 'W' = mur, ' ' = espace vide
    private char[][] map;
    private int height;
    private int width;
    private TileManager tileManager;
    // Liste qui contient tous les objets du donjon (murs, espaces)
    private ArrayList<Things> thingsList;

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.thingsList = new ArrayList<>();

        this.map = new char[height][width];
        // Je commence par dessiner la structure du donjon
        initializeMap();
        // Puis je transforme cette structure en objets utilisables
        fillThingsArray();
    }

    // Crée les murs sur les bords et des espaces au milieu
    private void initializeMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    map[i][j] = 'W';
                } else {
                    map[i][j] = ' ';
                }
            }
        }
    }

    // Transforme chaque caractère de la carte en objet Java
    // Les murs deviennent des SolidThings (avec collision), les espaces des Things simples
    private void fillThingsArray() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Conversion coordonnées tuile → pixels
                int pixelX = j * tileManager.getTileWidth();
                int pixelY = i * tileManager.getTileHeight();

                if (map[i][j] == 'W') {
                    // Mur = objet solide avec hitbox pour gérer les collisions
                    SolidThings wall = new SolidThings(pixelX, pixelY,
                            tileManager.getTileWidth(), tileManager.getTileHeight());
                    thingsList.add(wall);
                } else {
                    Things emptySpace = new Things(pixelX, pixelY,
                            tileManager.getTileWidth(), tileManager.getTileHeight());
                    thingsList.add(emptySpace);
                }
            }
        }
    }

    // Affiche le donjon dans la console avec la position du héros ('H')
    public void displayDungeonInConsole(int heroX, int heroY) {
        // Je convertis les coordonnées pixel du héros en coordonnées de tuile
        int heroTileX = heroX / tileManager.getTileWidth();
        int heroTileY = heroY / tileManager.getTileHeight();

        System.out.println("\n=== DONJON ===");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == heroTileY && j == heroTileX) {
                    System.out.print('H');
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("==============\n");
    }

    public ArrayList<Things> getThingsList() {
        return thingsList;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
