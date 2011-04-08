/** Classe Graphe.
    Impl�mente les Graphes sans boucles, pond�r�s ou non, dont l'ensemble des sommets est un sous-ensemble de l'Univers.
    Cette classe h�rite de Digraphe et contient les m�thodes sp�cifiques aux graphes.

    @author M.Marchand
    @version Octobre 2008
*/

   import java.util.*;
   import java.io.*;


    public class Graphe extends Digraphe {
    
      private static final int MAX = Elt.MAXELT.val();
      private int nombreDAr�tes;
   
   
   /** Construit un Graphe non pond�r� vide */
       public Graphe(){
         super();
         this.nombreDAr�tes = 0;
      }
   
   /** Construit un Graphe non pond�r� sans ar�te, dont l'ensemble de sommets est s */
       public Graphe(EnsembleInterface s){
         super(s);
         this.nombreDAr�tes = 0;
      }
   
   
   
   } // class Graphe

