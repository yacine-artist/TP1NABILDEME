Things (classe de base)
├── SolidThings (objets solides avec collision)
└── AnimatedThings (objets animables)
    └── DynamicThings (objets mobiles avec vitesse)

Hitbox (gestion des collisions)
TileManager (gestion des tuiles)
Dungeon (le monde du jeu)
Test (tests unitaires)
Main (point d'entrée)


Les Classes Principales
1. Hitbox.java
Rôle : Modéliser les zones de collision dans le jeu

Attributs :

x, y : Position du coin supérieur gauche

width, height : Dimensions de la hitbox

Méthodes clés :

intersect(Hitbox other) : Vérifie si deux hitbox se chevauchent

Logique : Compare les positions et dimensions pour détecter une collision

Retourne true si elles se chevauchent, false sinon

Exemple d'utilisation :


Hitbox h1 = new Hitbox(0, 0, 50, 50);
Hitbox h2 = new Hitbox(25, 25, 50, 50);
if (h1.intersect(h2)) {
    System.out.println("Collision détectée !");
}

Things.java
Rôle : Classe de base pour tous les objets du jeu

Attributs :

x, y : Position de l'objet

width, height : Dimensions de l'objet

Pourquoi protected ? Pour que les classes enfants accèdent directement aux attributs sans passer par des getters

Exemple :
Things objet = new Things(100, 50, 30, 30);
int posX = objet.getX(); // Récupère la position x

3. SolidThings.java
Rôle : Représenter les objets solides
