   import java.util.*;

    
    public class Test {
   
       public static void main(String[] args){
    	   /*Elt e = new Elt(10);
    	   Elt f = new Elt (11);
    	   Elt a = new Elt (1);
    	   Relation t = Io.chargerRelation("re4.rel");
    	   
    	   int dg1 = t.degreDeSortie(e);
    	   
    	   if(dg1 == 2) System.out.println("Test degreDeSortie OK");
    	   else System.out.println("Test degreSortie KO");
    	   
    	   int dg2 = t.degreDEntre(a);
    	   
    	   if(dg2 == 2) System.out.println("Test degreDEntre OK");
    	   else System.out.println("Test degreEntre KO");
    	   
    	   if(t.degreDEntre(f) != -1 || t.degreDeSortie(a)!=-1)System.out.println("erreur d'exception");
    	   
    	   System.out.println("Test image :");
    	   System.out.println(t.image());
    	   System.out.println(t.imageDirecte(f));
    	   Ensemble test = new Ensemble();
    	   test.ajouter(e);
    	   test.ajouter(f);
    	   System.out.println(t.imageDirecte(test));
    	   
    	   System.out.println("Test complémentaire :");
    	   Relation complementaire = t.complementaire();
    	   System.out.println(t);
    	   System.out.println(complementaire);
    	   
    	   System.out.println("Test réciproque");
    	   System.out.println(t);
    	   System.out.println(t.reciproque());
    	   
    	   System.out.println("Test ajouter rel");
    	   t.ajouter(t.reciproque());
    	   System.out.println(t);
    	   t.ajouter(complementaire);
    	   System.out.println(t);
    	   
    	   t = Io.chargerRelation("re4.rel");
    	   
    	   System.out.println("Test enlever rel");
    	   t.enlever(t);
    	   System.out.println("Le résultat attendu est l'ensemble vide : \n"+ t);
    	   
    	   t = Io.chargerRelation("re4.rel");
    	   
    	   System.out.println("test intersecter");
    	   //TO-DO
    	   Relation rel = Io.chargerRelation("re4.rel");
   		Relation test2 = new Relation();
   		Ensemble egaliter = new Ensemble();
   		Elt a2 = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);
   		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
   		Elt t2 = new Elt (1);Elt u = new Elt (2);Elt v = new Elt (3);Elt w = new Elt (4);Elt x = new Elt (5);Elt y = new Elt (6);Elt z = new Elt (7);
   		rel.intersecter(rel);
   		test2.ajouter(a2, t2); test2.ajouter(a2, u); test2.ajouter(a2, v); egaliter.ajouter(t2);egaliter.ajouter(u);
   		rel.intersecter(test2);
   		System.out.println(rel.image());
    	   
    	   System.out.println("test composée");
    	   //TO-DO
    	   
    	   System.out.println("test puissance");
    	   //TO-DO*/
    	   /*
    	   Relation rel = Io.chargerRelation("re4.rel");
   		Ensemble egaliter = new Ensemble();
   		
   		Elt a = new Elt (10);Elt b = new Elt (11);Elt c = new Elt (12);Elt d = new Elt (13);Elt e = new Elt (14);Elt f = new Elt (15);
   		Elt g = new Elt (16);Elt h = new Elt (17);Elt i = new Elt (18);Elt j = new Elt (19);Elt k = new Elt (20);
   		
   		egaliter.ajouter(a);egaliter.ajouter(b);egaliter.ajouter(c);egaliter.ajouter(f);egaliter.ajouter(g);egaliter.ajouter(j);
   		
   		rel = rel.après(rel.reciproque());
   		
   		System.out.println(rel.image());*/
    	   /*Relation rel = Io.chargerRelation("dg3.rel");
    	   System.out.println(rel.puissance(5));*/
    	   
    	   
    	/*   
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
   		
   		System.out.println(rel.chemin(new Elt(1), new Elt(48)));
   		*/
    	   
    	  /* Ordre or = new Ordre(Io.chargerRelation("or1.rel"));
    	   Ensemble sousEns = new Ensemble();
   		   sousEns.ajouter(new Elt(34)); sousEns.ajouter(new Elt(43)); sousEns.ajouter(new Elt(44)); sousEns.ajouter(new Elt(35));
    	   System.out.println(or.minor(sousEns));*/
    	   
    	Ordre or2 = new Ordre(Io.chargerRelation("or2.rel"));
   		System.out.println(or2.treillis());
      }  
   }                
        
