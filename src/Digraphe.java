/** Classe Digraphe.
    Impl�mente les digraphes, pond�r�s ou non, dont l'ensemble des sommets
    est un sous-ensemble de l'Univers.
    Cette classe h�rite de GrapheDeBase.

    @author M.Marchand
    @version Octobre 2008
*/

   import java.util.*;
   import java.io.*;


    public class Digraphe extends GrapheDeBase {
   
      private boolean pond�r�;  // statut
      
      private static final int MAX = Elt.MAXELT.val();
   
   /** Construit un digraphe non pond�r� vide */
       public Digraphe(){
         super();
         this.pond�r� = false;
      }
   
   /** Construit un digraphe non pond�r� sans ar�tes, dont l'ensemble de sommets est s */
       public Digraphe(EnsembleInterface s){
         super(s);
         this.pond�r� = false;
      }
   
   /** Construit, si possible, un digraphe non pond�r� � partir d'une relation */
       public Digraphe(RelationInterface r) throws MathException {
         super(r);
         this.pond�r� = false;
      }
   
   
   } // class Digraphe
