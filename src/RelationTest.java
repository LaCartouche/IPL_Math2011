import junit.framework.TestCase;

public class RelationTest extends TestCase{
	public void testDegreSortie(){
		try{
			Elt e = new Elt(10);
			Elt f = new Elt (11);
			Elt a = new Elt (1);
			Relation t = Io.chargerRelation("re4.rel");
  	   
			int dg1 = t.degreDeSortie(e);
			assertSame("Cas1: 10 a bien deux degré de sortie", 2, dg1);
			assertSame("Cas2: 1 n'a pas de degré de sortie", -1, t.degreDeSortie(a));
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void testDegreDEntre(){
		try{
			Elt e = new Elt(10);
			Elt f = new Elt (11);
			Elt a = new Elt (1);
			Relation t = Io.chargerRelation("re4.rel");
			
			int dg2 = t.degreDEntre(a);
			assertSame("Cas1 : 1 doit avoir deux degré d'entrée", 2, dg2);
			assertSame("Cas 2 : 10 n'a pas de degré de sortie", -1, t.degreDEntre(f));
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void testImage(){
		Elt e = new Elt(3);
		Elt f = new Elt (2);
		Elt a = new Elt (1);
		Elt b = new Elt(6);
		Elt c = new Elt(7);
		Relation t = Io.chargerRelation("re4.rel");
		Ensemble test = new Ensemble();
		
		test.ajouter(a);test.ajouter(e);test.ajouter(c);test.ajouter(b);test.ajouter(f);
		assertSame("Recherche de l'enseble image", true, test.estEgalA(t.image()));
	}
	
	public void testImageDirecte(){
		Elt e = new Elt(10);
		Elt f = new Elt (11);
		Elt b = new Elt (2);
		Elt a = new Elt (1);
		Relation t = Io.chargerRelation("re4.rel");
		Ensemble test = new Ensemble();
		test.ajouter(b);test.ajouter(a);
		
		assertSame("Cas 1 : recherche de l'ensemble image d'un element", true, test.estEgalA(t.imageDirecte(e)));
 	   	Ensemble cas2 = new Ensemble();
 	   	cas2.ajouter(e);cas2.ajouter(f);
 	   	assertSame("cas2 : recherche de l'ensemble image d'un ensemble", true, test.estEgalA(t.imageDirecte(cas2)));
	}
	
	public void testComplementaire(){
		Relation rel = Io.chargerRelation("re4.rel");
		Relation test = new Relation();
		
		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
		
		test.ajouter(a, v);test.ajouter(a, w);test.ajouter(a, x);test.ajouter(a, y);test.ajouter(a, z);test.ajouter(b, t);test.ajouter(b, v);test.ajouter(b, w);
		test.ajouter(b, x);test.ajouter(b, y);test.ajouter(b, z);test.ajouter(c, v);test.ajouter(c, w);test.ajouter(c, x);test.ajouter(c, y);test.ajouter(c, z);
		test.ajouter(d, t);test.ajouter(d, u);test.ajouter(d, v);test.ajouter(d, w);test.ajouter(d, x);test.ajouter(d, y);test.ajouter(d, z);test.ajouter(e, t);
		test.ajouter(e, u);test.ajouter(e, v);test.ajouter(e, w);test.ajouter(e, x);test.ajouter(e, y);test.ajouter(e, z);test.ajouter(f, t);test.ajouter(f, u);
		test.ajouter(f, w);test.ajouter(f, x);test.ajouter(f, z);test.ajouter(g, t);test.ajouter(g, u);test.ajouter(g, v);test.ajouter(g, w);test.ajouter(g, x);
		test.ajouter(g, y);test.ajouter(h, t);test.ajouter(h, u);test.ajouter(h, v);test.ajouter(h, w);test.ajouter(h, x);test.ajouter(h, y);test.ajouter(h, z);
		test.ajouter(i, t);test.ajouter(i, u);test.ajouter(i, v);test.ajouter(i, w);test.ajouter(i, x);test.ajouter(i, y);test.ajouter(i, z);test.ajouter(j, t);
		test.ajouter(j, u);test.ajouter(j, v);test.ajouter(j, w);test.ajouter(j, x);test.ajouter(j, y);test.ajouter(k, t);test.ajouter(k, u);test.ajouter(k, v);
		test.ajouter(k, w);test.ajouter(k, x);test.ajouter(k, y);test.ajouter(k, z);
		
		assertSame("Cas1 : verification des ensemble de depart", true , rel.complementaire().depart().estEgalA(rel.depart()));
		assertSame("Cas2 : verification des ensemble d'arrivé", true , rel.complementaire().arrivee().estEgalA(rel.arrivee()));
		assertSame("Cas3 : verification du contenu", true, rel.complementaire().image().estEgalA(test.image()));
	}
	
	public void testReciproque(){
		Relation rel = Io.chargerRelation("re4.rel");
		Relation test = new Relation();
		
		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
		
		test.ajouter(t, a);test.ajouter(t, c);test.ajouter(u, a);test.ajouter(u, b);test.ajouter(u, c); test.ajouter(v, f);test.ajouter(y, f);
		test.ajouter(z, g);test.ajouter(z, j);
		
		assertSame("Cas1 : verification des ensemble de depart", true , rel.reciproque().arrivee().estEgalA(rel.depart()));
		assertSame("Cas2 : verification des ensemble d'arrivé", true , rel.reciproque().depart().estEgalA(rel.arrivee()));
		assertSame("Cas3 : verification du contenu", true, rel.reciproque().image().estEgalA(test.image()));
	}
	
	public void testAjouter(){
		Relation rel = Io.chargerRelation("re4.rel");
		Ensemble test = new Ensemble();
		rel.ajouter(rel.complementaire());
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
		
		test.ajouter(t);test.ajouter(u);test.ajouter(v);test.ajouter(w);test.ajouter(x);test.ajouter(y);test.ajouter(z);
		
		assertSame("Cas1 : verification du contenu", true, rel.arrivee().estEgalA(test));
	}
	
	public void testEnlever(){
		Relation rel = Io.chargerRelation("re4.rel");
		Ensemble test = new Ensemble();
		rel.enlever(rel);
		
		assertSame("Cas1 : le contenu doit etre null", true, rel.image().estEgalA(test));
	}
	
	public void testIntersecter(){
		Relation rel = Io.chargerRelation("re4.rel");
		Relation test = new Relation();
		Ensemble egaliter = new Ensemble();
		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
		
		test.ajouter(a, t); test.ajouter(a, u); test.ajouter(a, v); egaliter.ajouter(t);egaliter.ajouter(u);
		 rel.intersecter(test);
		assertSame("Cas1 : verification de l'ensemble image", true, egaliter.estEgalA(rel.image()));
	}
	
	public void testApres(){
		Relation rel = Io.chargerRelation("re4.rel");
		Ensemble egaliter = new Ensemble();
		
		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		
		egaliter.ajouter(a);egaliter.ajouter(b);egaliter.ajouter(c);egaliter.ajouter(f);egaliter.ajouter(g);egaliter.ajouter(j);
		
		rel = rel.apres(rel.reciproque());
		
		assertSame("Cas1 : verification de l'ensemble image", true, rel.image().estEgalA(egaliter));
	}
	
	public void testPuissance(){
		Relation rel = Io.chargerRelation("dg3.rel");
		Relation test = new Relation();

		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
	}
	
	public void testInclusDans(){
		Relation rel = Io.chargerRelation("re4.rel");
		Relation test = new Relation();
		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
		Elt t = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
		
		test.ajouter(a, t); test.ajouter(a, u);
		
		assertSame("Cas1 : verification de l'inclus dans", true, test.inclusDans(rel));
	}
	
	public void testEstEgaleA(){
		Relation rel = Io.chargerRelation("dg1.rel");
		assertSame("Verification d'une auto egalisté", true, rel.estEgaleA(rel));
	}
	
	public void testReflexive(){
		Relation test = new Relation();
		Elt a = new Elt(1); Elt b = new Elt(2);
		test.ajouter(a, a);test.ajouter(b, b);test.ajouter(a, b);
		assertSame("Verification de la reflexivité", true, test.reflexive());
	}
	
	public void testAntiRflexive(){
		Relation rel = Io.chargerRelation("dg2.rel");
		assertSame("Verification anti-reflexif", true, rel.antireflexive());
	}
	
	public void testSymetrique(){
		Relation rel = Io.chargerRelation("gr1.rel");
		assertSame("Verification symetrie", true, rel.symetrique());
	}
	
	public void testAntiSymetrique(){
		Relation test = new Relation();
		Elt a = new Elt(1);Elt b = new Elt(2);
		test.ajouter(a, b);test.ajouter(a, a);test.ajouter(b, b);
		Relation rel = Io.chargerRelation("dg3.rel");
		assertSame("Verification creation", true, test.antisymetrique());
		assertSame("Verification anti-symetrie", true, rel.antisymetrique());
	}
	
	public void testTransitive(){
		Relation rel = Io.chargerRelation("dg5.rel");
		Relation test = new Relation(rel.depart(), rel.arrivee());
		test.ajouter(rel);
		assertSame("Verification transitivité", true, rel.transitive());
		assertSame("Verification nn modif de la rel", true, test.estEgaleA(rel));
	}
	
	public void testCirculaire(){
		Relation rel = Io.chargerRelation("dg1.rel");
		Relation test = new Relation(rel.depart(), rel.arrivee());
		test.ajouter(rel);
		assertSame("Verification circulaire", true, rel.circulaire());
		assertSame("Verification nn modif de la rel", true, test.estEgaleA(rel));
	}
	
	public void testAcyclique(){
		Relation rel = Io.chargerRelation("dg5.rel");
		assertSame("Verification acyclique", true, rel.acyclique());
	}
	
	public void testChemin(){
		Relation rel = Io.chargerRelation("dg2.rel");
		Suite suite = new Suite();
		suite.ajouter(new Elt(48));
		suite.ajouter(new Elt(44));
		suite.ajouter(new Elt(37));
		suite.ajouter(new Elt(30));
		suite.ajouter(new Elt(15));
		suite.ajouter(new Elt(6));
		suite.ajouter(new Elt(5));
		suite.ajouter(new Elt(4));
		suite.ajouter(new Elt(12));
		suite.ajouter(new Elt(11));
		suite.ajouter(new Elt(19));
		suite.ajouter(new Elt(9));
		suite.ajouter(new Elt(1));
		assertTrue(rel.chemin(new Elt(1), new Elt(48)).equals2(suite));
	}
	
	public void testFaiblementConnexe(){
		Relation rel1 = Io.chargerRelation("eq1.rel");
		Relation rel2 = Io.chargerRelation("eq2.rel");
		Relation rel3 = Io.chargerRelation("dg1.rel");
		Relation rel4 = Io.chargerRelation("dg2.rel");
		Relation rel5 = Io.chargerRelation("dg3.rel");
		Relation rel6 = Io.chargerRelation("dg4.rel");
		Relation rel7 = Io.chargerRelation("dg5.rel");
		Relation rel8 = Io.chargerRelation("gr1.rel");
		Relation rel9 = Io.chargerRelation("gr2.rel");
		Relation rel10 = Io.chargerRelation("or1.rel");
		Relation rel11 = Io.chargerRelation("or2.rel");
		Relation rel12 = Io.chargerRelation("or3.rel");
		assertFalse(rel1.faiblementConnexe());
		assertFalse(rel2.faiblementConnexe());
		assertFalse(rel3.faiblementConnexe());
		assertFalse(rel5.faiblementConnexe());
		assertFalse(rel6.faiblementConnexe());
		assertFalse(rel7.faiblementConnexe());
		assertFalse(rel8.faiblementConnexe());
		assertFalse(rel12.faiblementConnexe());
		assertTrue(rel4.faiblementConnexe());
		assertTrue(rel9.faiblementConnexe());
		assertTrue(rel10.faiblementConnexe());
		assertTrue(rel11.faiblementConnexe());
	}
	
	public void testConnexe(){
		Relation rel1 = Io.chargerRelation("eq1.rel");
		Relation rel2 = Io.chargerRelation("eq2.rel");
		Relation rel3 = Io.chargerRelation("dg1.rel");
		Relation rel4 = Io.chargerRelation("dg2.rel");
		Relation rel5 = Io.chargerRelation("dg3.rel");
		Relation rel6 = Io.chargerRelation("dg4.rel");
		Relation rel7 = Io.chargerRelation("dg5.rel");
		Relation rel8 = Io.chargerRelation("gr1.rel");
		Relation rel9 = Io.chargerRelation("gr2.rel");
		Relation rel10 = Io.chargerRelation("or1.rel");
		Relation rel11 = Io.chargerRelation("or2.rel");
		Relation rel12 = Io.chargerRelation("or3.rel");
		assertFalse(rel1.connexe());
		assertFalse(rel2.connexe());
		assertFalse(rel3.connexe());
		assertFalse(rel5.connexe());
		assertFalse(rel6.connexe());
		assertFalse(rel7.connexe());
		assertFalse(rel8.connexe());
		assertFalse(rel12.connexe());
		assertFalse(rel4.connexe());
		assertFalse(rel10.connexe());
		assertFalse(rel11.connexe());
		assertTrue(rel9.connexe());
	}
	
	public void testFortementConnexe(){
		Relation rel1 = Io.chargerRelation("eq1.rel");
		Relation rel2 = Io.chargerRelation("eq2.rel");
		Relation rel3 = Io.chargerRelation("dg1.rel");
		Relation rel4 = Io.chargerRelation("dg2.rel");
		Relation rel5 = Io.chargerRelation("dg3.rel");
		Relation rel6 = Io.chargerRelation("dg4.rel");
		Relation rel7 = Io.chargerRelation("dg5.rel");
		Relation rel8 = Io.chargerRelation("gr1.rel");
		Relation rel9 = Io.chargerRelation("gr2.rel");
		Relation rel10 = Io.chargerRelation("or1.rel");
		Relation rel11 = Io.chargerRelation("or2.rel");
		Relation rel12 = Io.chargerRelation("or3.rel");
		assertFalse(rel1.fortementConnexe());
		assertFalse(rel2.fortementConnexe());
		assertFalse(rel3.fortementConnexe());
		assertFalse(rel5.fortementConnexe());
		assertFalse(rel6.fortementConnexe());
		assertFalse(rel7.fortementConnexe());
		assertFalse(rel8.fortementConnexe());
		assertFalse(rel12.fortementConnexe());
		assertFalse(rel10.fortementConnexe());
		assertFalse(rel11.fortementConnexe());
		assertTrue(rel4.fortementConnexe());
		assertTrue(rel9.fortementConnexe());
	}
}