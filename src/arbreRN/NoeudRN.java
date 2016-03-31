package arbreRN;



import javafx.scene.paint.Color;

public class NoeudRN {
    Color couleur;
    Comparable info;
    NoeudRN gauche;
    NoeudRN droit, parent; 
    NoeudRN(Comparable o){ 
        couleur = Color.BLACK; 
        info = o; 
        gauche = droit = parent = null;
    } 
    NoeudRN(Comparable o, Color c, NoeudRN g, NoeudRN d, NoeudRN p){
        couleur = c;
        info = o;
        gauche = g;
        droit = d;
        parent = p;
    }
    boolean isSentinelle(){
        return this == ArbreRN.sentinelle;
    }
    int getHauteur(){ 
        if(isSentinelle())return 0;
        else return 1+Math.max(gauche.getHauteur(),droit.getHauteur()); 
    }
    boolean recherche( Comparable o)
    { 
    if(isSentinelle()) return false;
    if(info.equals(o)) return true;
    if(info.compareTo(o) == -1) return droit.recherche(o);
    if(info.compareTo(o) == 1) return gauche.recherche(o);
    return false;
    }
    
}
