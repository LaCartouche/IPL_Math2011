import java.util.Iterator;

public class DossMath11 {
	private static final double BASE = 3700;
	private static final double PRIME = 45;
	private static final double BONUS = 200;
	private static final Relation COL = Io.chargerRelation("Col.rel");
	private static final Relation FIN = Io.chargerRelation("Fin.rel");
	private static final Relation CPT = Io.chargerRelation("Cpt.rel");
	private static final Relation SUP = Io.chargerRelation("Sup.rel");
	private static final Relation CCN = Io.chargerRelation("Ccn.rel");
	private static final Relation SEX = Io.chargerRelation("Sex.rel");

	private static final String[] tPers = Io.chargerDta("Pers.dta");
	private static final int nbPers = Integer.parseInt(tPers[0]); // nombre de
																	// membres
																	// du
																	// personnel

	private static final String[] tProj = Io.chargerDta("Proj.dta");
	private static final int nbProj = Integer.parseInt(tProj[0]); // nombre
																	// d'options

	private static final String[] tQual = Io.chargerDta("Qual.dta");
	private static final int nbQual = Integer.parseInt(tQual[0]);// nombre de
																	// cours

	private static Ensemble chercheurs = COL.domaine();
	private static Ensemble administratifs = administratifs();
	// Pas encore utilisé
	// private static Ensemble financiers = FIN.domaine();

	// LAP - Liste d'affectation possible
	private static Relation LAP = CCN.reciproque().apres(CPT);
	// Nombre de personne affectable par projet
	private static Ordre PVP = ex2_3();
	// PDG de l'entreprise
	private static Elt PDG = ex3_3();
	// Pondération correspondant à la relation SUP
	private static int[] rang = ponderationSUP();

	public static void main(String[] args) throws MathException {
		// ex1_1();
		// ex1_2();
		// ex1_3();
		// ex1_4();
		// ---------------
		// ex2_1();
		// ex2_2();
		// ex2_3();
		// ex2_4();
		// ex2_5();
		// ex2_6();
		// ---------------
		// ex3_1();
		// ex3_2();
		ex3_3();
		// ex3_4();
		// ---------------
		ex4_1();
		// ex4_2();
		// ---------------
		// ex6_1();
		ex6_2();
		// ex6_3();
	} // main

	// Chaque membre du personnel à au moins un sexe...
	public static Ensemble administratifs() {
		Ensemble tmp = SEX.clone().domaine();
		tmp.enlever(chercheurs);
		return tmp;
	}

	public static void ex1_1() {
		System.out.println("Liste des chercheurs");
		lister(chercheurs, "PERSONNELS");
	}

	public static void ex1_2() {
		System.out.println("Liste des administratifs");
		lister(administratifs, "PERSONNELS");
	}

	public static void ex1_3() {
		// EN ATTENTE: classe application souhaitée
	}

	public static void ex1_4() {
		System.out
				.println("Chercheurs qui collaborent au maximum de projets : ");
		Ensemble ensChercheurs = new Ensemble();
		int nbProjets = 0;
		Iterator<Elt> itChercheur = chercheurs.iterator();
		while (itChercheur.hasNext()) {
			Elt unChercheur = itChercheur.next();
			if (COL.degreDeSortie(unChercheur) > nbProjets) {
				ensChercheurs = new Ensemble();
				nbProjets = COL.degreDeSortie(unChercheur);
				ensChercheurs.ajouter(unChercheur);
			} else if (COL.degreDeSortie(unChercheur) == nbProjets) {
				ensChercheurs.ajouter(unChercheur);
			}
		}
		lister(ensChercheurs, "PERSONNELS");
	}

	// ---------------------------------------------------------------------------

	public static void ex2_1() {
		System.out.println("Question 2 : ");
		// Relation créé en global
		// Relation LAP
	}

	public static void ex2_2() {
		lister(LAP.imageReciproque(numéro("PAMAL", "PROJETS")), "PERSONNELS");
	}

	public static Ordre ex2_3() {
		Ordre or = new Ordre(LAP.arrivee());
		Iterator<Elt> it1 = or.arrivee().iterator();
		Ensemble ens = LAP.arrivee().clone();
		while (it1.hasNext()) {
			Elt elt = it1.next();
			Iterator<Elt> it2 = ens.iterator();
			while (it2.hasNext()) {
				Elt elt2 = it2.next();
				if (LAP.degreDEntre(elt) < LAP.degreDEntre(elt2))
					or.ajouter(new Couple(elt, elt2));
			}
		}
		return or;
	}

	public static void ex2_4() {
		System.out
				.println("Certains projets ne sont pas comparables entre eux");
		System.out
				.println("(Ceux ayant le même nombres de personnels affectables)");
	}

	public static void ex2_5() {
		System.out.println("Cette ordre est-t-il total : "
				+ PVP.total(PVP.depart()));
	}

	public static void ex2_6() {
		lister(PVP.minimaux(PVP.depart()), "PROJETS");
		lister(CCN.imageDirecte(PVP.minimaux(PVP.depart())), "QUALIFICATIONS");
	}

	// ---------------------------------------------------------------------------

	public static void ex3_1() {
		Relation COMPE = CPT.apres(COL.reciproque()).clone();
		System.out.println("Les projets sont-ils bien gérés : "
				+ COMPE.arrivee().estEgalA(CCN.arrivee()));

	}

	public static void ex3_2() {
		if (SUP.acyclique())
			System.out.println("La relation SUP est bien acyclique");
		else
			System.out.println("La relation SUP n'est pas acyclique");
	}

	public static Elt ex3_3() {
		Ordre or = new Ordre(SUP);
		Elt min = or.minimum(or.depart());
		if (min != null) {
			System.out.print("PDG : ");
			lister(new Ensemble(min), "PERSONNELS");
			return min;
		} else
			System.out.println("L'entreprise n'a pas de PDG");
		return null;
	}

	public static void ex3_4() {
		boolean correct = true;
		Iterator<Elt> it = LAP.image().iterator();
		while (it.hasNext()) {
			Elt projet = it.next();
			if (!COL.imageReciproque(projet).inclusDans(
					LAP.imageReciproque(projet)))
				correct = false;
		}
		if (correct)
			System.out.println("Tout est en ordre");
		else
			System.out
					.println("Un chercheur participe à un projet auquel il ne devrait pas.");
	}

	// ---------------------------------------------------------------------------

	public static void ex4_1() {
		double salaire;
		Iterator<Elt> itMembre = SEX.domaine().iterator();
		while (itMembre.hasNext()) {
			Elt membre = itMembre.next();
			salaire = calculSalaire(membre);
			lister(new Ensemble(membre), "PERSONNELS");
			System.out.println("\t\t\t" + salaire + " euros");
		}
	}

	// Pondération de la relation SUP afin d'accéder aisément au rang d'un membre
	private static int[] ponderationSUP() {
		Ordre or = new Ordre(SUP);
		int[] rang = new int[or.depart().cardinal()+1];
		Iterator<Elt> it = SUP.depart().iterator();
		while (it.hasNext()) {
			Elt rechercher = it.next();
			int cpt = 0;
			Elt superieurCourant = rechercher;
			while (!superieurCourant.estEgalA(PDG)) {
				Ensemble temp = SUP.imageReciproque(superieurCourant);
				temp.enlever(superieurCourant);
				Elt min = or.minimum(temp);
				if (min == null) {
					superieurCourant = or.minimaux(temp).unElement();
				} else
					superieurCourant = min;
				cpt++;
			}
			rang[rechercher.val()] = cpt;
		}
		return rang;
	}

	// Calcul du salaire pour la question 4.1
	private static double calculSalaire(Elt membre) {
		double salaire = BASE;
		double DELTA = 0.78;
		double bonus = 0;
		// On cherche le niveau du membre
		int niveauMembre = rang[membre.val()];
		// On calcule sa base
		for (int i = 0; i < niveauMembre; i++)
			salaire *= DELTA;
		// Ensuite on calcule la PRIME en fonction du nombre de personnes
		// auxquelles on est supérieur
		salaire += PRIME * (SUP.imageDirecte(membre).cardinal());
		// Et on ajoute le BONUS de chaque projet auxquel il participe
		Ensemble projetsDuMembre = FIN.imageDirecte(membre);
		projetsDuMembre = Ensemble.intersection(projetsDuMembre, FIN.image());
		Iterator<Elt> itProjet = projetsDuMembre.iterator();
		while (itProjet.hasNext()) {
			Elt projet = itProjet.next();
			bonus += COL.imageReciproque(projet).cardinal() * BONUS / FIN.imageReciproque(projet).cardinal();
		}
		salaire += bonus;
		salaire *= 100;
		salaire = Math.round(salaire);
		return salaire / 100;
	}

	public static void ex4_2() {

	}

	// ---------------------------------------------------------------------------

	public static void ex6_1() {
		Iterator<Elt> it = CCN.depart().iterator();
		while (it.hasNext()) {
			Elt e = it.next();
			System.out.println("Electeur du projet " + e.val() + " "
					+ tProj[e.val()] + " :");
			electeur(e);
		}
	}

	public static void ex6_2() {
		Relation rel = FIN.apres(COL.reciproque());
		if (rel.reflexive())
			System.out
					.println("Tous les projets ont au moins un parrain possible");
		else {
			System.out.println(rel);
			Ensemble projetSansParrainsPossible = new Ensemble();
			Iterator<Elt> it = rel.depart().iterator();
			while (it.hasNext()) {
				Elt elt = it.next();
				if (!rel.contient(elt, elt))
					projetSansParrainsPossible.ajouter(elt);
			}
			lister(projetSansParrainsPossible, "PROJETS");
		}
	}

	public static void ex6_3() {
		// FAUX
		Ensemble parrain = Ensemble.intersection(FIN.domaine(), COL.domaine());
		// ensuite voir ceux qui sont les seul de leur projet
		Iterator<Elt> it = FIN.arrivee().iterator();
		while (it.hasNext()) {
			Elt x = it.next();
			Ensemble a = FIN.imageReciproque(x).inter(parrain);
			Ensemble b = COL.imageReciproque(x).inter(parrain);
			if (a.cardinal() != 1)
				parrain.enlever(a);
			if (b.cardinal() != 1)
				parrain.enlever(b);
		}
		lister(parrain, "PERSONNELS");
	}

	private static void electeur(Elt projet) {
		Ordre SUP2 = new Ordre(SUP);
		Ensemble electeur = new Ensemble();
		Ensemble temp = COL.imageReciproque(projet).union(
				FIN.imageReciproque(projet));
		electeur.ajouter(temp);
		electeur.ajouter(SUP2.getRel().imageReciproque(temp));
		lister(electeur, "PERSONNELS");
	}

	// affiche à l'écran les éléments de e, interprétés selon contexte
	private static void lister(Ensemble e, String contexte)
			throws MathException {
		String[] t;
		String ct;
		if (contexte.toUpperCase().equals("PERSONNELS")) {
			t = tPers;
			ct = "de membres du personnel";
		} else if (contexte.toUpperCase().equals("PROJETS")) {
			t = tProj;
			ct = "de projets";
		} else if (contexte.toUpperCase().equals("QUALIFICATIONS")) {
			t = tQual;
			ct = "de domaines de qualification";
		} else
			throw new MathException("contexte incorrect");
		if (e.estVide())
			System.out.println("Ensemble vide " + ct);
		else {
			Iterator<Elt> eit = e.iterator();
			while (eit.hasNext()) {
				int x = eit.next().val();
				System.out.println("        " + x + "\t" + t[x]);
			}
		}
	} // lister

	// renvoie le Elt correpondant à nom, d'après contexte; null si incorrect
	private static Elt numéro(String nom, String contexte) throws MathException {
		String[] t;
		int stop;
		if (contexte.toUpperCase().equals("PERSONNELS")) {
			t = tPers;
			stop = nbPers;
		} else if (contexte.toUpperCase().equals("PROJETS")) {
			t = tProj;
			stop = nbProj;
		} else if (contexte.toUpperCase().equals("QUALIFICATIONS")) {
			t = tQual;
			stop = nbQual;
		} else
			throw new MathException("contexte incorrect");
		for (int i = 1; i <= stop; i++)
			if (t[i].toUpperCase().equals(nom.toUpperCase()))
				return new Elt(i);
		return null;
	} // numéro

} // class
