/** Classe Digraphe.
    Implémente les digraphes, pondérés ou non, dont l'ensemble des sommets
    est un sous-ensemble de l'Univers.
    Cette classe hérite de GrapheDeBase.

    @author M.Marchand
    @version Octobre 2008
*/

   import java.util.*;
   import java.io.*;


    public class Digraphe extends GrapheDeBase {
   
      private boolean pondéré;  // statut
      
      private static final int MAX = Elt.MAXELT.val();
   
   /** Construit un digraphe non pondéré vide */
       public Digraphe(){
         super();
         this.pondéré = false;
      }
   
   /** Construit un digraphe non pondéré sans arêtes, dont l'ensemble de sommets est s */
       public Digraphe(EnsembleInterface s){
         super(s);
         this.pondéré = false;
      }
   
   /** Construit, si possible, un digraphe non pondéré à partir d'une relation */
       public Digraphe(RelationInterface r) throws MathException {
         super(r);
         this.pondéré = false;
      }
   
   
   } // class Digraphe
