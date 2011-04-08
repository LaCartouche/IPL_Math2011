/** Classe Relation héritant de RelationDeBase
	 Fournit des outils de manipulation des relations entre sous-ensembles de l'Univers

	 @author M.Marchand
	 @version mars 2008
 */

import java.util.*;

public class Relation extends RelationDeBase {

	/** Valeur numérique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	/** Construit la Relation vide sur l'ensemble vide */
	public Relation() {
		super();
	}

	/** Construit la Relation vide de d vers a */
	public Relation(EnsembleInterface d, EnsembleInterface a) {
		super(d, a);
	}
	
	// Renvoie la relation qui correspond au produit cartésien de 2 ensembles
	public static Relation produitCartesien(EnsembleInterface a,
			EnsembleInterface b) {
		Iterator<Elt> itA = a.iterator();
		Relation produit = new Relation(a, b);
		while (itA.hasNext()) {
			Iterator<Elt> itB = b.iterator();
			Elt x = itA.next();
			while (itB.hasNext()) {
				produit.ajouter(x, itB.next());
			}
		}
		return produit;
	}

	/** Renvoie un clone de la Relation courante */
	public Relation clone() {
		Relation cl = new Relation(this.depart(), this.arrivee());
		Iterator<Couple> it = this.iterator();
		while (it.hasNext())
			cl.ajouter(it.next());
		return cl;
	}

	/** Clôture la Relation courante pour la réflexivité */
	public void cloReflex() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException("Hors sujet : cloRéflex()");
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt x = it.next();
			this.ajouter(x, x);
		}
	}

	/** Clôture la Relation courante pour la symétrie */
	public void cloSym() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException("Hors sujet : cloSym()");
		Iterator<Elt> itd = this.depart().iterator();
		Ensemble reste = new Ensemble(this.depart());
		while (itd.hasNext()) {
			Elt x = itd.next();
			reste.enlever(x);
			Iterator<Elt> itr = reste.iterator();
			while (itr.hasNext()) {
				Elt y = itr.next();
				if (this.contient(x, y) || this.contient(y, x)) {
					this.ajouter(x, y);
					this.ajouter(y, x);
				}
			}
		}
	}

	/** Clôture la Relation courante pour la transitivité (Warshall) */
	public void cloTrans() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException("Hors sujet : cloTrans()");
		Iterator<Elt> it1 = this.depart().iterator();
		while (it1.hasNext()) {
			Elt x = it1.next();
			Iterator<Elt> it2 = this.depart().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (this.contient(y, x)) {
					Iterator<Elt> it3 = this.depart().iterator();
					while (it3.hasNext()) {
						Elt z = it3.next();
						if (this.contient(x, z))
							this.ajouter(y, z);
					}
				}
			}
		}
	}

	public int degreDEntre(Elt x) {
		if (!this.arrivee().contient(x))
			return -1;
		int de = 0;
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			if (this.contient(it.next(), x))
				de++;
		}
		return de;
	}

	public int degreDeSortie(Elt x) {
		if (!this.depart().contient(x))
			return -1;
		int ds = 0;
		Iterator<Elt> it = this.arrivee().iterator();
		while (it.hasNext()) {
			if (this.contient(x, it.next()))
				ds++;
		}
		return ds;
	}

	public Ensemble imageDirecte(EnsembleInterface e) {
		if (e == null)
			return null;
		Ensemble image = new Ensemble();
		Iterator<Elt> it = e.iterator();
		while (it.hasNext()) {
			image = image.union(this.imageDirecte(it.next()));
		}
		return image;
	}

	public Ensemble image() {
		Ensemble image = new Ensemble();
		Iterator<Elt> it = this.arrivee().iterator();
		while (it.hasNext()) {
			Elt e = it.next();
			if (this.degreDEntre(e) > 0) {
				image.ajouter(e);
			}
		}
		return image;
	}

	public Ensemble imageDirecte(Elt i) {
		if (!this.depart().contient(i))
			return null;
		Iterator<Elt> it = this.arrivee().iterator();
		Ensemble e = new Ensemble();
		while (it.hasNext()) {
			Elt x = it.next();
			if (this.contient(i, x)) {
				e.ajouter(x);
			}
		}
		return e;
	}

	/** AJOUT */
	public Ensemble domaine() {
		Ensemble domaine = new Ensemble();
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt e = it.next();
			if (this.degreDeSortie(e) > 0) {
				domaine.ajouter(e);
			}
		}
		return domaine;
	}

	public Ensemble imageReciproque(Elt e) {
		if (!this.arrivee().contient(e))
			return null;
		Iterator<Elt> it = this.depart().iterator();
		Ensemble ens = new Ensemble();
		while (it.hasNext()) {
			Elt x = it.next();
			if (this.contient(x, e)) {
				ens.ajouter(x);
			}
		}
		return ens;
	}
	

	/** AJOUT */
	public Ensemble imageReciproque(EnsembleInterface e) {
		if (e == null)
			return null;
		Ensemble image = new Ensemble();
		Iterator<Elt> it = e.iterator();
		while (it.hasNext()) {
			image = image.union(this.imageReciproque(it.next()));
		}
		return image;
	}

	public Relation complementaire() {
		Iterator<Elt> it = this.depart().iterator();
		Relation r = new Relation();
		while (it.hasNext()) {
			Elt x = it.next();
			Iterator<Elt> it2 = this.arrivee().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (!this.contient(x, y)) {
					r.ajouter(x, y);
				}
			}
		}
		return r;
	}

	// renvoie la complémentaire de la Relation courante

	public Relation reciproque() {
		Iterator<Elt> it = this.depart().iterator();
		Relation r = new Relation(this.arrivee(), this.depart());
		while (it.hasNext()) {
			Elt x = it.next();
			Iterator<Elt> it2 = this.arrivee().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (this.contient(x, y)) {
					r.ajouter(y, x);
				}
			}
		}
		return r;
	}

	// renvoie la réciproque de la Relation courante

	public void ajouter(RelationInterface r) {
		if (!this.depart().estEgalA(r.depart()))
			return;
		if (!this.arrivee().estEgalA(r.arrivee()))
			return;

		Iterator<Elt> it2 = r.depart().iterator();
		while (it2.hasNext()) {
			Elt x = it2.next();
			Iterator<Elt> it3 = r.arrivee().iterator();
			while (it3.hasNext()) {
				Elt y = it3.next();
				Couple c = new Couple(x, y);
				if (r.contient(c)) {
					this.ajouter(x, y);
				}
			}
		}
	}

	// si possible, remplace la Relation courante par son union avec r

	public void enlever(RelationInterface r) {
		if (!this.depart().estEgalA(r.depart()))
			return;
		if (!this.arrivee().estEgalA(r.arrivee()))
			return;

		Iterator<Elt> it2 = r.depart().iterator();
		while (it2.hasNext()) {
			Elt x = it2.next();
			Iterator<Elt> it3 = r.arrivee().iterator();
			while (it3.hasNext()) {
				Elt y = it3.next();
				Couple c = new Couple(x, y);
				if (r.contient(c)) {
					this.enlever(c);
				}
			}
		}
	}

	// si possible, remplace this par sa différence avec r

	public void intersecter(RelationInterface r) {
		if (this.depart().inter(r.depart()).estVide())
			return;
		if (this.arrivee().inter(r.arrivee()).estVide())
			return;

		Iterator<Elt> it2 = this.depart().iterator();
		while (it2.hasNext()) {
			Elt x = it2.next();
			Iterator<Elt> it3 = this.arrivee().iterator();
			while (it3.hasNext()) {
				Elt y = it3.next();
				Couple c = new Couple(x, y);
				if (!(r.contient(c)) && this.contient(c)) {
					this.enlever(c);
				}
			}
		}
	}

	// si possible, remplace this par son intersection avec r

	public Relation apres(RelationInterface r) {
		if (!r.arrivee().estEgalA(this.depart()))
			throw new MathException(
					"Opération illégale : Relations non composables");
		Relation thisor = new Relation(r.depart(), this.arrivee());
		Iterator<Elt> itd = thisor.depart().iterator();
		while (itd.hasNext()) {
			Elt x = itd.next();
			Iterator<Elt> ita = thisor.arrivee().iterator();
			while (ita.hasNext()) {
				Elt y = ita.next();
				Elt z = new Elt(MAX);
				do {
					z = z.succ();
					if (r.contient(new Couple(x, z)) && this.contient(z, y)) {
						thisor.ajouter(x, y);
						break;
					}
				} while (!z.estEgalA(Elt.MAXELT));
			}
		}
		return thisor;
	}

	// si possible, renvoie la composée « this après r »

	public Relation puissance(int k) {
		Relation puissance = this;
		for (int i = 0; i < k; i++) {
			puissance = puissance.apres(puissance);
		}
		return puissance;
	}

	// si possible, renvoie la kième puissance de la Relation courante
	public boolean inclusDans(RelationInterface r) {
		Iterator<Elt> it2 = this.depart().iterator();
		while (it2.hasNext()) {
			Elt x = it2.next();
			Iterator<Elt> it3 = this.arrivee().iterator();
			while (it3.hasNext()) {
				Elt y = it3.next();
				Couple c = new Couple(x, y);
				if (!(r.contient(c)) && this.contient(c)) {
					return false;
				}
			}
		}
		return true;
	}

	// renvoie true ssi this est dans r

	public boolean estEgaleA(RelationInterface r) {
		if (!this.depart().estEgalA(r.depart()))
			return false;
		if (!this.arrivee().estEgalA(r.arrivee()))
			return false;

		Iterator<Elt> it2 = this.depart().iterator();
		while (it2.hasNext()) {
			Elt x = it2.next();
			Iterator<Elt> it3 = this.arrivee().iterator();
			while (it3.hasNext()) {
				Elt y = it3.next();
				Couple c = new Couple(x, y);
				if (!(r.contient(c)) && this.contient(c)) {
					return false;
				}
				if (r.contient(c) && !this.contient(c)) {
					return false;
				}
			}
		}
		return true;
	}

	// renvoie true ssi this = r

	public boolean reflexive(EnsembleInterface e) {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException();
		if (!e.inclusDans(this.depart()))
			throw new MathException();
		Iterator<Elt> it = e.iterator();
		while (it.hasNext()) {
			Elt i = it.next();
			if (!this.contient(i, i))
				return false;
		}
		return true;
	}

	// renvoie true ssi la restriction de this à e est réflexive

	public boolean reflexive() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException();
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt i = it.next();
			if (!this.contient(i, i))
				return false;
		}
		return true;
	}

	// renvoie true ssi this est réflexive

	public boolean antireflexive() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException();
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt i = it.next();
			if (this.contient(i, i))
				return false;
		}
		return true;
	}

	// renvoie true ssi this est antiréflexive

	public boolean symetrique() {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException();
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt x = it.next();
			Iterator<Elt> it2 = this.arrivee().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (this.contient(x, y) && !this.contient(y, x))
					return false;
			}
		}
		return true;
	}

	// renvoie true ssi this est symétrique

	public boolean antisymetrique() {// cas ==
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException();
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt x = it.next();
			Iterator<Elt> it2 = this.arrivee().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (this.contient(x, y) && this.contient(y, x)
						&& !x.estEgalA(y))
					return false;
			}
		}
		return true;
	}

	// renvoie true ssi this est antisymétrique

	public boolean transitive() {
		Relation trans = this;
		return trans.apres(trans).inclusDans(trans);
	}

	// renvoie true ssi this est transitive

	public boolean circulaire() {
		Relation circ = this;
		return circ.apres(circ).inclusDans(circ.reciproque());
	}

	// renvoie true ssi this est circulaire

	public boolean acyclique() {
		Relation acyc = new Relation(this.depart(), this.arrivee());
		Iterator<Elt> it = this.depart().iterator();
		while (it.hasNext()) {
			Elt x = it.next();
			Iterator<Elt> it2 = this.arrivee().iterator();
			while (it2.hasNext()) {
				Elt y = it2.next();
				if (this.contient(x, y))
					acyc.ajouter(x, y);
			}
		}
		acyc.cloTrans();
		return acyc.antireflexive();
	}

	// renvoie true ssi this est acyclique

	public Suite chemin(Elt x, Elt y) {
		Suite unChemin = new Suite(y);
		Elt elt = null;
		Boolean ajout;

		// Ensemble des Elt à parcourir
		Ensemble aParcourir = this.depart().clone();
		// Inutile de tester y
		aParcourir.enlever(y);

		while (!aParcourir.estVide() && !unChemin.estVide() && !this.contient(x, unChemin.tete())) {
			ajout = false;
			Iterator<Elt> it = aParcourir.iterator();
			while (it.hasNext() && !ajout) {
				elt = it.next();
				if (this.contient(elt, unChemin.tete())) {
					ajout = true;
				}
			}
			// Si on a un chemin, on vérifie plus loin
			if (ajout) {
				aParcourir.enlever(elt);
				unChemin.ajouter(elt);
				// Sinon on revient à une intersection ou pas de chemin
			} else {
				unChemin.couper();
			}
		}
		// Si aParcourir est vide c'est qu'il n'y a pas de chemin de x à y
		if (!aParcourir.estVide())
			unChemin.ajouter(x);
		// On retire donc le y ajouté en début d'algorithme
		else
			unChemin.couper();
		return unChemin;
	}
	
	/*public Suite chemin(Elt a, Elt b) {
		if (!this.depart().estEgalA(this.arrivee()))
			throw new MathException("Hors sujet : chemin(a,b)");
		Suite chem = new Suite();
		chem.ajouter(b);
		Ensemble interdit = new Ensemble();
		if (!b.equals(a))
			interdit.ajouter(b);
		Elt x = null;
		boolean ok = true;
		while (!chem.estVide() && !this.contient(a, chem.tete())) {
			ok = false;
			Iterator<Elt> it = this.depart().iterator();
			while (it.hasNext() && !ok) {
				x = it.next();
				ok = this.contient(x, chem.tete()) && !interdit.contient(x);
			}
			if (ok) {
				chem.ajouter(x);
				interdit.ajouter(x);
			} else
				chem.couper();
		}
		if (ok)
			chem.ajouter(a);
		return chem;
	}*/
	
	

	public boolean faiblementConnexe() {
		Relation clone = this.clone();
		// En effectuant ces deux clôtures, on a des relations entre chaque
		// élément
		// vers tous les autres (entre ceux reliés)
		clone.cloSym();
		clone.cloTrans();
		// Le seul moyen que le complémentaire ne soit pas vide
		// est que le graphe ne soit pas d'un seul tenant
		return clone.complementaire().estVide();
	}

	// renvoie true ssi this est faiblement connexe

	public boolean fortementConnexe() {
		Relation clone = this.clone();
		clone.cloTrans();
		if (clone.estEgaleA(produitCartesien(this.depart(), this.arrivee())))
			return true;
		return false;
	}

	// renvoie true ssi this est fortement connexe

	public boolean connexe() {
		if (this.fortementConnexe() && this.symetrique())
			return true;
		return false;
	}
	// renvoie true ssi this est connexe

} // class Relation