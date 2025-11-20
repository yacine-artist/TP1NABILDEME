public class Test {
    public static void main(String[] args) {
        System.out.println("=== TESTS DE LA CLASSE HITBOX ===\n");

        // Test 1 : Deux hitbox qui se chevauchent
        testIntersectionChevauchement();

        // Test 2 : Deux hitbox qui ne se touchent pas
        testPasIntersection();

        // Test 3 : Deux hitbox qui se touchent juste sur un bord
        testIntersectionBord();

        // Test 4 : Une hitbox complètement dans une autre
        testHitboxIncluse();

        // Test 5 : Deux hitbox identiques (même position)
        testHitboxIdentiques();

        // Test 6 : Test avec des coordonnées négatives
        testCoordonneesNegatives();

        System.out.println("\n=== TOUS LES TESTS SONT TERMINÉS ===");
    }

    // Test 1 : Vérifier que deux hitbox qui se chevauchent sont détectées
    private static void testIntersectionChevauchement() {
        System.out.println("Test 1 : Intersection avec chevauchement");
        Hitbox h1 = new Hitbox(0, 0, 50, 50);
        Hitbox h2 = new Hitbox(25, 25, 50, 50);

        System.out.println("  Hitbox 1 : " + h1);
        System.out.println("  Hitbox 2 : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        // Je vérifie que le résultat est correct
        if (resultat) {
            System.out.println("  ✓ TEST RÉUSSI : Les hitbox se chevauchent bien\n");
        } else {
            System.out.println("  ✗ TEST ÉCHOUÉ : Elles devraient se chevaucher\n");
        }
    }

    // Test 2 : Vérifier que deux hitbox séparées ne sont pas détectées
    private static void testPasIntersection() {
        System.out.println("Test 2 : Pas d'intersection (hitbox séparées)");
        Hitbox h1 = new Hitbox(0, 0, 30, 30);
        Hitbox h2 = new Hitbox(100, 100, 30, 30);

        System.out.println("  Hitbox 1 : " + h1);
        System.out.println("  Hitbox 2 : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        if (!resultat) {
            System.out.println("  ✓ TEST RÉUSSI : Les hitbox sont bien séparées\n");
        } else {
            System.out.println("  ✗ TEST ÉCHOUÉ : Elles ne devraient pas se toucher\n");
        }
    }

    // Test 3 : Vérifier le cas limite où les hitbox se touchent exactement sur un bord
    private static void testIntersectionBord() {
        System.out.println("Test 3 : Hitbox qui se touchent sur un bord");
        Hitbox h1 = new Hitbox(0, 0, 50, 50);
        Hitbox h2 = new Hitbox(50, 0, 50, 50);

        System.out.println("  Hitbox 1 : " + h1);
        System.out.println("  Hitbox 2 : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        // Dans ce cas, elles ne se chevauchent pas vraiment (juste le bord)
        if (!resultat) {
            System.out.println("  ✓ TEST RÉUSSI : Les bords se touchent mais pas d'intersection\n");
        } else {
            System.out.println("  ℹ INFO : Selon l'implémentation, le contact de bord peut être considéré comme intersection\n");
        }
    }

    // Test 4 : Une hitbox complètement incluse dans une autre
    private static void testHitboxIncluse() {
        System.out.println("Test 4 : Une hitbox incluse dans une autre");
        Hitbox h1 = new Hitbox(0, 0, 100, 100);
        Hitbox h2 = new Hitbox(25, 25, 20, 20);

        System.out.println("  Hitbox 1 (grande) : " + h1);
        System.out.println("  Hitbox 2 (petite) : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        if (resultat) {
            System.out.println("  ✓ TEST RÉUSSI : La petite hitbox est bien détectée dans la grande\n");
        } else {
            System.out.println("  ✗ TEST ÉCHOUÉ : L'inclusion devrait être détectée\n");
        }
    }

    // Test 5 : Deux hitbox avec exactement la même position
    private static void testHitboxIdentiques() {
        System.out.println("Test 5 : Deux hitbox identiques (même position)");
        Hitbox h1 = new Hitbox(50, 50, 40, 40);
        Hitbox h2 = new Hitbox(50, 50, 40, 40);

        System.out.println("  Hitbox 1 : " + h1);
        System.out.println("  Hitbox 2 : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        if (resultat) {
            System.out.println("  ✓ TEST RÉUSSI : Deux hitbox identiques s'intersectent\n");
        } else {
            System.out.println("  ✗ TEST ÉCHOUÉ : Elles devraient s'intersecter\n");
        }
    }

    // Test 6 : Test avec des coordonnées négatives
    private static void testCoordonneesNegatives() {
        System.out.println("Test 6 : Hitbox avec coordonnées négatives");
        Hitbox h1 = new Hitbox(-50, -50, 60, 60);
        Hitbox h2 = new Hitbox(-10, -10, 30, 30);

        System.out.println("  Hitbox 1 : " + h1);
        System.out.println("  Hitbox 2 : " + h2);

        boolean resultat = h1.intersect(h2);
        System.out.println("  Résultat : " + resultat);

        if (resultat) {
            System.out.println("  ✓ TEST RÉUSSI : Les coordonnées négatives sont gérées correctement\n");
        } else {
            System.out.println("  ✗ TEST ÉCHOUÉ : Elles devraient s'intersecter\n");
        }
    }
}
