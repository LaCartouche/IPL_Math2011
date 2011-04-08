/** Classe Graphe.
    Implémente les Graphes sans boucles, pondérés ou non, dont l'ensemble des sommets est un sous-ensemble de l'Univers.
    Cette classe hérite de Digraphe et contient les méthodes spécifiques aux graphes.

    @author M.Marchand
    @version Octobre 2008
*/

   import java.util.*;
   import java.io.*;


    public class Graphe extends Digraphe {
    
      private static final int MAX = Elt.MAXELT.val();
      private int nombreDArêtes;
   
   
   /** Construit un Graphe non pondéré vide */
       public Graphe(){
         super();
         this.nombreDArêtes = 0;
      }
   
   /** Construit un Graphe non pondéré sans arête, dont l'ensemble de sommets est s */
       public Graphe(EnsembleInterface s){
         super(s);
         this.nombreDArêtes = 0;
      }
   
   
   
   } // class Graphe

