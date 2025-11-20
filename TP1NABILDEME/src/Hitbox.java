public class Hitbox {
    // Coordonnées du coin supérieur gauche de la hitbox
    // J'utilise des coordonnées x,y pour pouvoir positionner la hitbox dans l'espace 2D du jeu
    private int x;
    private int y;

    // Dimensions de la hitbox (largeur et hauteur)
    // Ces valeurs définissent la taille de la zone de collision
    private int width;
    private int height;

    // Constructeur : permet de créer une hitbox en spécifiant sa position et sa taille
    // J'ai besoin de tous ces paramètres dès la création pour que la hitbox soit utilisable immédiatement
    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters : ces méthodes permettent de lire les valeurs des attributs privés
    // C'est important pour l'encapsulation - on ne peut pas accéder directement aux attributs depuis l'extérieur
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

    // Setters : permettent de modifier la position de la hitbox après sa création
    // Utile quand un objet se déplace dans le jeu et que sa hitbox doit suivre
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Méthode la plus importante : détecte si deux hitbox se chevauchent
    // C'est crucial pour gérer les collisions entre objets du jeu (murs, personnages, etc.)
    public boolean intersect(Hitbox other) {
        // Logique de détection : je vérifie d'abord si les rectangles NE se chevauchent PAS
        // Si un rectangle est complètement à gauche ou à droite de l'autre, pas de collision
        if (this.x + this.width <= other.x || other.x + other.width <= this.x) {
            return false;
        }
        // Même vérification sur l'axe vertical : si un rectangle est complètement au-dessus ou en-dessous
        if (this.y + this.height <= other.y || other.y + other.height <= this.y) {
            return false;
        }
        // Si aucune des conditions ci-dessus n'est vraie, alors les rectangles se chevauchent
        return true;
    }

    // Override de toString() pour faciliter le débogage
    // Quand je fais System.out.println() d'une Hitbox, j'obtiens directement ses infos
    @Override
    public String toString() {
        return "Hitbox[x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + "]";
    }
}
