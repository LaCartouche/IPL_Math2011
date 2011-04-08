import junit.framework.TestCase;

public class OrdreTest extends TestCase{
	
	public void testTotal() {
		Ensemble ens = new Ensemble();
		ens.ajouter(new Elt(18)); ens.ajouter(new Elt(19)); ens.ajouter(new Elt(20)); ens.ajouter(new Elt(23)); ens.ajouter(new Elt(25));
		ens.ajouter(new Elt(27)); ens.ajouter(new Elt(28)); ens.ajouter(new Elt(32)); ens.ajouter(new Elt(35));
		Ordre or = new Ordre(Io.chargerRelation("or2.rel"));
		assertTrue(or.total(ens));
	}
	
	public void testMinor() {
		Ordre or = new Ordre(Io.chargerRelation("or1.rel"));
		Ensemble minorants = new Ensemble();
		minorants.ajouter(new Elt(11)); minorants.ajouter(new Elt(32)); minorants.ajouter(new Elt(33));
		minorants.ajouter(new Elt(46)); minorants.ajouter(new Elt(10)); minorants.ajouter(new Elt(34));
		Ensemble sousEns = new Ensemble();
		sousEns.ajouter(new Elt(34)); sousEns.ajouter(new Elt(43)); sousEns.ajouter(new Elt(44)); sousEns.ajouter(new Elt(35));
		assertTrue(or.minor(sousEns).estEgalA(minorants));
	}
	
	public void testMaximaux() {
		Ordre or = new Ordre(Io.chargerRelation("or1.rel"));
		Ensemble maximaux = new Ensemble();
		maximaux.ajouter(new Elt(36));
		Ordre or2 = new Ordre(Io.chargerRelation("or3.rel"));
		Ensemble maximaux2 = new Ensemble();
		maximaux2.ajouter(new Elt(37)); maximaux2.ajouter(new Elt(7)); maximaux2.ajouter(new Elt(26));
		assertTrue(or.maximaux(or.depart()).estEgalA(maximaux));
		assertTrue(or2.maximaux(or2.depart()).estEgalA(maximaux2));
	}
	
	public void testTreillis() {
		Ordre or = new Ordre(Io.chargerRelation("or1.rel"));
		or.enlever(new Couple(46,34));
		assertTrue(or.treillis());
		Ordre or2 = new Ordre(Io.chargerRelation("or2.rel"));
		assertTrue(or2.treillis());
		Ordre or3 = new Ordre(Io.chargerRelation("or3.rel"));
		assertFalse(or3.treillis());
	}

}
