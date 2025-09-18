package liste;

public class ListeSimple {
    private int size;
    Noeud tete;

    public int getSize() {
        return size;
    }

    public void ajout(Object element) {
        tete = new Noeud(element, tete);
        size++;
    }

    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && (courant.getElement() == null || !courant.getElement().equals(element))) {
            courant = courant.getSuivant();
        }
        if (courant != null) {
            courant.setElement(nouvelleValeur);
        }
    }

    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() != null && courant.getElement().equals(element)) {
                courant.setElement(nouvelleValeur);
            }
            courant = courant.getSuivant();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    public void supprimePremier(Object element) {
        if (tete == null) return;

        if (tete.getElement() != null && tete.getElement().equals(element)) {
            tete = tete.getSuivant();
            size--;
            return;
        }

        Noeud precedent = tete;
        Noeud courant = tete.getSuivant();
        while (courant != null && (courant.getElement() == null || !courant.getElement().equals(element))) {
            precedent = courant;
            courant = courant.getSuivant();
        }

        if (courant != null) {
            precedent.setSuivant(courant.getSuivant());
            size--;
        }
    }

    public void supprimeTous(Object element) {
        tete = supprimeTousRecurs(element, tete);
    }

    private Noeud supprimeTousRecurs(Object element, Noeud noeud) {
        if (noeud == null) return null;

        Noeud suiteListe = supprimeTousRecurs(element, noeud.getSuivant());

        if (noeud.getElement() != null && noeud.getElement().equals(element)) {
            size--;
            return suiteListe;
        } else {
            noeud.setSuivant(suiteListe);
            return noeud;
        }
    }

    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null) return null;

        Noeud courant = tete;
        while (courant.getSuivant().getSuivant() != null) {
            courant = courant.getSuivant();
        }
        return courant;
    }

    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    public Noeud getPrecedent(Noeud r) {
        if (r == tete) return null;
        Noeud precedent = tete;
        Noeud courant = tete.getSuivant();
        while (courant != null && courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return courant == r ? precedent : null;
    }

    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2) return;

        Noeud prevR1 = (r1 == tete) ? null : getPrecedent(r1);
        Noeud prevR2 = (r2 == tete) ? null : getPrecedent(r2);

        if (prevR1 != null) prevR1.setSuivant(r2);
        if (prevR2 != null) prevR2.setSuivant(r1);

        if (r1 == tete) tete = r2;
        else if (r2 == tete) tete = r1;

        Noeud temp = r1.getSuivant();
        r1.setSuivant(r2.getSuivant());
        r2.setSuivant(temp);
    }
}
