import java.awt.Image;

public final class Hero extends DynamicThings {

    private static volatile Hero instance = null;

    // Constructeur privé
    private Hero() {
        // On crée le héros en position 0,0 sans image pour l'instant (null)
        // On changera ça plus tard quand on aura chargé les images
        super(0, 0, null);
        System.out.println("Le héros est créé !");
    }

    public static Hero getInstance() {
        if (instance == null) {
            synchronized (Hero.class) {
                if (instance == null) {
                    instance = new Hero();
                }
            }
        }
        return instance;
    }
}