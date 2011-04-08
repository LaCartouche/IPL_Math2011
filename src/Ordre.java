import java.util.Iterator;

public class Ordre implements RelationInterface {

	private static int MAX = Elt.MAXELT.val();
	private Relation rel;

	public Ordre(EnsembleInterface e) {
		if (e == null)
			throw new NullPointerException();
		rel = new Relation(e, e);
		rel.cloReflex();
	}

	// construit l’identité sur e

	public Ordre(Ordre or) {
		if (or == null)
			throw new NullPointerException();
		this.rel = or.rel.clone();
	}

	// constructeur par recopie

	public Ordre(Relation r) {
		if (r == null)
			throw new NullPointerException();
		Relation temp = r.clone();
		temp.cloTrans();
		temp.cloReflex();
		if (!r.antisymetrique())
			throw new MathException();
		rel = temp;
	}

	// construit le plus petit ordre contenant r ;
	// génère une MathException si cette construction est impossible

	public boolean estVide() {
		Iterator<Elt> it = this.rel.depart().iterator();
		while (it.hasNext()) {
			if (this.rel.degreDeSortie(it.next()) > 1)
				return false;
		}
		return true;
	}

	public boolean contient(Couple c) {
		return this.rel.contient(c);
	}

	public void ajouter(Elt x) {
		this.rel.ajouter(x, x);
	}

	public void ajouter(Couple c) {
		if (this.contient(c.réciproque()))
			throw new MathException();
		this.ajouter(c.getx());
		this.ajouter(c.gety());
		rel.ajouter(c);
		rel.cloTrans();

	}

	public void enlever(Couple c) {
		if (this.contient(c)) {
			Iterator<Elt> itx = rel.imageReciproque(c.getx()).iterator();
			while (itx.hasNext()) {
				Elt x = itx.next();
				Iterator<Elt> ity = rel.imageDirecte(c.gety()).iterator();
				while (ity.hasNext()) {
					rel.enlever(x, ity.next());
				}
			}
			rel.cloTrans();
		}
	}

	public EnsembleInterface depart() {
		return this.rel.depart();
	}

	public EnsembleInterface arrivee() {
		return this.rel.arrivee();
	}

	public String toString() {
		return rel.toString();
	}

	// Renvoie true si deux éléments sont comparables entre eux
	public boolean comparables(Elt x, Elt y) {
		return this.contient(new Couple(x, y))
				|| this.contient(new Couple(y, x));
	}

	// Renvoie true ssi pour l'Ordre courant, e est totalement ordonné
	public boolean total(EnsembleInterface e) {
		Iterator<Elt> it = e.iterator();
		Ensemble aComparer = new Ensemble(e);
		while (it.hasNext()) {
			Elt elt1 = it.next();
			aComparer.enlever(elt1);
			Iterator<Elt> itComp = aComparer.iterator();
			while (itComp.hasNext()) {
				Elt elt2 = itComp.next();
				if (!comparables(elt1, elt2))
					return false;
			}
		}
		return true;
	}

	// Renvoie l'ensemble des éléments minimaux de b
	public Ensemble minimaux(EnsembleInterface b) {
		/*
		 * if(!b.inclusDans(this.rel.depart()))return null; Ensemble temp = new
		 * Ensemble(); Iterator<Elt> it = b.iterator(); while(it.hasNext()){ Elt
		 * a = it.next(); boolean aAjouter = true; Iterator<Elt> it2 =
		 * this.rel.imageReciproque(a).iterator(); while(it2.hasNext()){ Elt z =
		 * it2.next(); if(b.contient(z)&&z!=a)aAjouter = false; }
		 * if(aAjouter)temp.ajouter(a); } return temp;
		 */
		Ensemble minim = new Ensemble();
		Iterator<Elt> it1 = Ensemble.intersection(b, this.depart()).iterator();
		boolean ok;
		while (it1.hasNext()) {
			Elt x = it1.next();
			ok = true;
			Iterator<Elt> it2 = (new Ensemble(minim)).iterator();
			while (it2.hasNext() && ok) {
				Elt y = it2.next();
				if (this.contient(new Couple(x, y)))
					minim.enlever(y);
				if (this.contient(new Couple(y, x)))
					ok = false;
			}
			if (ok)
				minim.ajouter(x);
		}
		return minim;
	}

	public Relation getRel() {
		return rel;
	}

	public Ensemble maximaux(EnsembleInterface b) {
		Ensemble maxim = new Ensemble();
		Iterator<Elt> it1 = Ensemble.intersection(b, this.depart()).iterator();
		boolean ok;
		while (it1.hasNext()) {
			Elt x = it1.next();
			ok = true;
			Iterator<Elt> it2 = (new Ensemble(maxim)).iterator();
			while (it2.hasNext() && ok) {
				Elt y = it2.next();
				if (this.contient(new Couple(y, x)))
					maxim.enlever(y);
				if (this.contient(new Couple(x, y)))
					ok = false;
			}
			if (ok)
				maxim.ajouter(x);
		}
		return maxim;
	}

	// Renvoie le minimum de b s'il existe; renvoie null sinon
	public Elt minimum(EnsembleInterface b) {
		if (!b.inclusDans(this.rel.depart()))
			return null;
		if (this.minimaux(b).cardinal() == 1)
			return this.minimaux(b).unElement();
		return null;
	}

	public Elt maximum(EnsembleInterface b) {
		if (!b.inclusDans(this.rel.depart()))
			return null;
		if (this.maximaux(b).cardinal() == 1)
			return this.maximaux(b).unElement();
		return null;
	}

	// Renvoie l'ensemble des minorants de b
	public Ensemble minor(EnsembleInterface b) {
		Ensemble min = this.minimaux(b);
		Ensemble retour = new Ensemble();
		Iterator<Elt> itMin = min.iterator();
		if (itMin.hasNext()) {
			retour = rel.imageReciproque(itMin.next());
		}
		while (itMin.hasNext()) {
			retour.intersecter(rel.imageReciproque(itMin.next()));
			if (retour.estVide())
				return new Ensemble();
		}
		return retour;
	}
	
	public Ensemble major(EnsembleInterface b) {
		Ensemble maj = this.maximaux(b);
		Ensemble retour = new Ensemble();
		Iterator<Elt> itMaj = maj.iterator();
		if (itMaj.hasNext()) {
			retour = rel.imageDirecte(itMaj.next());
		}
		while (itMaj.hasNext()) {
			retour.intersecter(rel.imageDirecte(itMaj.next()));
			if (retour.estVide())
				return new Ensemble();
		}
		return retour;
	}

	// Renvoie l'infimum de b s'il existe; renvoie null sinon
	public Elt infimum(EnsembleInterface b) {
		if (!b.inclusDans(this.rel.depart()))
			return null;
		return this.maximum(this.minor(b));
	}
	
	public Elt supremum(EnsembleInterface b) {
		if (!b.inclusDans(this.rel.depart()))
			return null;
		return this.minimum(this.major(b));
	}

	// Renvoie true ssi a est un élément minimal de b
	public boolean minimal(Elt a, EnsembleInterface b) {
		return this.minimaux(b).contient(a);
	}
	
	public boolean maximal(Elt a, EnsembleInterface b) {
		return this.maximaux(b).contient(a);
	}

	// Renvoie true ssi a est un minorant de b
	public boolean minorant(Elt a, EnsembleInterface b) {
		return this.minor(b).contient(a);
	}
	
	public boolean majorant(Elt a, EnsembleInterface b) {
		return this.major(b).contient(a);
	}
	
	public boolean treillis() {
		Iterator<Elt> it = this.depart().iterator();
		Ensemble aComparer = new Ensemble(this.depart());
		Ensemble paire;
		while (it.hasNext()) {
			Elt elt1 = it.next();
			aComparer.enlever(elt1);
			Iterator<Elt> itComp = aComparer.iterator();
			while (itComp.hasNext()) {
				Elt elt2 = itComp.next();
				paire = new Ensemble();
				paire.ajouter(elt1);
				paire.ajouter(elt2);
				if ((this.supremum(paire) == null) || (this.infimum(paire) == null))
					return false;
			}
		}
		return true;
	}

}