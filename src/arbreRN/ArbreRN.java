package arbreRN;



import javafx.scene.paint.Color;

public class ArbreRN {
    static NoeudRN sentinelle;
    static {
    ArbreRN.sentinelle =new NoeudRN(null,Color.BLACK,null, null,null);
    }
    NoeudRN racine,noeudAjoute;
    
public ArbreRN(){ 
    racine=ArbreRN.sentinelle; 
} 

public void ajout( Comparable o)
{ 
racine = ajout(o, racine, null); 
reOrganiser(noeudAjoute);
}
private NoeudRN ajout( Comparable o, NoeudRN r, NoeudRN p){
   if (r.isSentinelle())
        r = noeudAjoute = new NoeudRN(o, Color.RED, r, r, p);
    else 
        if(o.compareTo(r.info)<0) r.gauche = ajout(o, r.gauche, r); 
        else r.droit = ajout(o, r.droit, r);
return r; 
}
private void rotationGauche(NoeudRN n){
NoeudRN y = n.droit;
n.droit = y.gauche;
if(!y.gauche.isSentinelle()) y.gauche.parent = n;
y.parent = n.parent; 
if (n.parent==null) racine = y; 
else 
    if( n.parent.gauche==n) n.parent.gauche=y;
    else
        n.parent.droit = y;
    y.gauche = n;
    n.parent = y;
}
private void rotationDroite( NoeudRN n){
    NoeudRN y = n.gauche;
    n.gauche = y.droit;
    if(!y.droit.isSentinelle()) 
        y.droit.parent = n;
    y.parent = n.parent; 
    if (n.parent==null) 
        racine = y; 
    else 
        if( n.parent.droit== n) n.parent.droit= y; 
        else n.parent.gauche = y; 
    y.droit = n; 
    n.parent = y;
}
private void reOrganiser( NoeudRN n){
    while(n != racine && n.parent.couleur == Color.RED ){
        if(n.parent == n.parent.parent.gauche)
        { 
            NoeudRN y = n.parent.parent.droit;
            if( y.couleur == Color.RED ){
            n.parent.couleur = Color.BLACK;
            y.couleur = Color.BLACK;
            n.parent.parent.couleur = Color.RED;
            n = n.parent.parent; 
        }
        else{ 
            if(n == n.parent.droit){
                n = n.parent;
                rotationGauche(n);
            }
            n.parent.couleur=Color.BLACK;
            n.parent.parent.couleur= Color.RED;
            rotationDroite(n.parent.parent); 
        }
        }
        else{
            NoeudRN y = n.parent.parent.gauche;
            if( y.couleur == Color.RED ){ n.parent.couleur = Color.BLACK; y.couleur = Color.BLACK; n.parent.parent.couleur = Color.RED; n = n.parent.parent; }
            else{ 
                if(noeudAjoute==n.parent.gauche){n=n.parent;rotationDroite(n);}
                n.parent.couleur = Color.BLACK;
                n.parent.parent.couleur = Color.RED;
                rotationGauche(n.parent.parent); 
                }
              }
        } 
    racine.couleur = Color.BLACK; // la racine est toujours noire }
    
}
public void supprimer( Comparable o){
   supprimer( racine, o );
}

private NoeudRN supprimer( NoeudRN r, Comparable o){
   if(r.isSentinelle())return r; // Pas trouvé
   if(o.compareTo(r.info)== 0) r = supprimer(r);
   else 
      if(o.compareTo(r.info)<0)
          supprimer(r.gauche, o);
      else supprimer(r.droit, o);
            return r;
}
// La méthode suivante réalise la suppression du nœud, en le remplaçant par le plus petit élément de son sous-arbre droit, s’il a deux fils, et en le supprimant effectivement, s’il n’a qu’un seul fils.

private NoeudRN supprimer( NoeudRN z){
        NoeudRN y;
        NoeudRN x;
   if(z.gauche.isSentinelle() || z.droit.isSentinelle()) y = z;
   else y = arbreSuccesseur(z);
   if(!y.gauche.isSentinelle()) x = y.gauche;
   else x = y.droit;
   x.parent = y.parent;
   if(y.parent== null) racine = x;
   else
      if( y == y.parent.gauche) y.parent.gauche = x;
      else y.parent.droit = x;
   if( y != z ) z.info = y.info; 
 if ( y.couleur == Color.BLACK) reOrganiserSuppression( x );
   return y;
}
private NoeudRN arbreSuccesseur(NoeudRN x){
   if( !x.droit.isSentinelle()) return arbreMinimum(x.droit);
   NoeudRN y = x.parent;
   while(!y.isSentinelle() && x == y.droit){ x = y; y = x.parent;}
   return y;
}

private NoeudRN arbreMinimum(NoeudRN x){
   while( !x.gauche.isSentinelle()) x = x.gauche;
   return x;
}

private void reOrganiserSuppression( NoeudRN n){
   while(n != racine && n.couleur == Color.BLACK ){
      if(n == n.parent.gauche){
         NoeudRN y = n.parent.droit;
         if(y.couleur == Color.RED ){
            y.couleur = Color.BLACK;
            n.parent.couleur = Color.RED;
            rotationGauche(n.parent);
            y = n.parent.droit;
         }
         if(y.gauche.couleur == Color.BLACK && y.droit.couleur == Color.BLACK){
            y.couleur = Color.RED;
            n = n.parent;
         }else{
            if( y.droit.couleur == Color.BLACK){
               y.gauche.couleur = Color.BLACK;
               y.couleur = Color.RED;
               rotationDroite(y);
               y = n.parent.droit;
            }
            y.couleur = n.parent.couleur;
            n.parent.couleur = Color.BLACK;
            y.droit.couleur = Color.BLACK;
            rotationGauche(n.parent);
            break;
         }
      }else{
         NoeudRN y = n.parent.gauche;
         if( y.couleur == Color.RED ){
            y.couleur = Color.BLACK;
            n.parent.couleur = Color.RED;
            rotationDroite(n.parent);
            y = n.parent.gauche;
         }
         if(y.droit.couleur == Color.BLACK && y.gauche.couleur == Color.BLACK){
            y.couleur = Color.RED;
            n = n.parent;
         }else{
            if( y.gauche.couleur == Color.BLACK){
               y.droit.couleur = Color.BLACK;
               y.couleur = Color.RED;
               rotationGauche(y);
               y = n.parent.gauche;
            }
            y.couleur = n.parent.couleur;
            n.parent.couleur = Color.BLACK;
            y.gauche.couleur = Color.BLACK;
            rotationDroite(n.parent);
            break;
         }
      }
   }
   n.couleur = Color.BLACK;
}
}
