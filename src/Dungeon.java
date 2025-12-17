import java.util.ArrayList;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dungeon {

    private ArrayList<Things> renderList = new ArrayList<>();
    private char[][] map;
    private int width;
    private int height;
    private TileManager tileManager;

    public Dungeon(String fileName, TileManager tileManager) {
        this.tileManager = tileManager;

        try {
            loadMap(fileName);
            buildMap();
        } catch (IOException e) {
            System.out.println("Erreur map : " + e.getMessage());
        }
    }

    private void loadMap(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        height = lines.size();
        width = lines.get(0).length();
        map = new char[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = lines.get(y).charAt(x);
            }
        }
    }

    private void buildMap() {
        renderList.clear();

        // ✅ TUILES PROPRES (PAS DE TRANSITION)
        Image grass = tileManager.getTile(0, 0);      // herbe simple
        Image path  = tileManager.getTile(1, 0);      // pierre simple
        Image wall  = tileManager.getTile(0, 12);     // mur / arbre
        Image box   = tileManager.getTile(10, 9);     // caisse

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int px = x * 32;
                int py = y * 32;
                char c = map[x][y];

                // 🟩 FOND (toujours dessiné)
                if (c == 'D') {
                    renderList.add(new Things(px, py, path));
                } else {
                    renderList.add(new Things(px, py, grass));
                }

                // 🟥 OBSTACLES PAR-DESSUS
                if (c == 'W') {
                    renderList.add(new SolidThings(px, py, wall));
                }
                if (c == 'T') {
                    renderList.add(new SolidThings(px, py, box));
                }
            }
        }
    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
