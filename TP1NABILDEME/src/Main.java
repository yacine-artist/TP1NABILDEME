public class Main {
    public static void main(String[] args) {
        System.out.println("=== Démarrage du TP1 - Moteur de Donjon ===\n");

        TileManager tm = new TileManager(32, 32);
        Dungeon dungeon = new Dungeon(15, 20, tm);

        int heroX = 3 * tm.getTileWidth();
        int heroY = 3 * tm.getTileHeight();

        dungeon.displayDungeonInConsole(heroX, heroY);

        System.out.println("=== Test des collisions ===");
        testCollisions(tm);

        System.out.println("\n=== Statistiques ===");
        System.out.println("Nombre total d'objets : " + dungeon.getThingsList().size());
        System.out.println("Dimensions du donjon : " + dungeon.getWidth() + "x" + dungeon.getHeight() + " tuiles");
        System.out.println("Dimensions en pixels : " +
                (dungeon.getWidth() * tm.getTileWidth()) + "x" +
                (dungeon.getHeight() * tm.getTileHeight()) + " pixels");
    }

    private static void testCollisions(TileManager tm) {
        Hitbox h1 = new Hitbox(0, 0, 50, 50);
        Hitbox h2 = new Hitbox(25, 25, 50, 50);
        System.out.println("Hitbox 1 : " + h1);
        System.out.println("Hitbox 2 : " + h2);
        System.out.println("Collision détectée : " + h1.intersect(h2));

        Hitbox h3 = new Hitbox(0, 0, 30, 30);
        Hitbox h4 = new Hitbox(100, 100, 30, 30);
        System.out.println("\nHitbox 3 : " + h3);
        System.out.println("Hitbox 4 : " + h4);
        System.out.println("Collision détectée : " + h3.intersect(h4));
    }
}
