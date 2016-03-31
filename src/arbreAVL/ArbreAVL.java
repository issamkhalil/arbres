package arbreAVL;

import arbreRN.NoeudRN;

public class ArbreAVL{
    Comparable contenu;
    int haut;
    ArbreAVL filsG,filsD;

ArbreAVL(Comparable c, ArbreAVL g, ArbreAVL d){
    super();
    filsG= g;
    contenu = c; 
    filsD= d;
    haut= 1 + Math.max(hauteur(g), hauteur(d));
    }

public ArbreAVL(Comparable contenu){
    this(contenu,null,null);
}

public static int hauteur(ArbreAVL a){
    if(a== null) return -1;
    return 1+Math.max(hauteur(a.filsG),hauteur(a.filsD));
}

public static void prefixe(ArbreAVL a){
if (a!=null) {
System.out.print(a.contenu +" "); prefixe(a.filsG); prefixe(a.filsD);
}
}

private static void calculerHauteur(ArbreAVL a){
    if(a!=null){
    calculerHauteur(a.filsG);
    calculerHauteur(a.filsD);
    a.haut= 1 + Math.max(hauteur(a.filsG), hauteur(a.filsD));
}
}

private static ArbreAVL rotationG(ArbreAVL a){
    ArbreAVL b= a.filsD;
    ArbreAVL c = new ArbreAVL(a.contenu, a.filsG, b.filsG);
    ArbreAVL r=new ArbreAVL(b.contenu,c,b.filsD);
    return r;
}
private static ArbreAVL rotationD(ArbreAVL a){
    ArbreAVL c = a.filsG;
    ArbreAVL b = new ArbreAVL(a.contenu, c.filsD, a.filsD);
    ArbreAVL r=new ArbreAVL(c.contenu, c.filsG,b);
    return r;
}
private static ArbreAVL equilibrer(ArbreAVL a){
    calculerHauteur(a);
    if (hauteur(a.filsG) -hauteur(a.filsD) == 2)
    {
        if (hauteur(a.filsG.filsG) < hauteur(a.filsG.filsD))
        a.filsG= rotationG(a.filsG);
        return rotationD(a);
    } // else
    if (hauteur(a.filsG)-hauteur(a.filsD) == -2)
    {
        if (hauteur(a.filsD.filsD) < hauteur(a.filsD.filsG))
        a.filsD= rotationD(a.filsD);
        return rotationG(a);
    }
    return a;
}

  public static int GetnbFeuille(ArbreAVL r){
        if(r==null)
            return 0;
        return 1+(GetnbFeuille(r.filsD)+GetnbFeuille(r.filsG));
    }

public static ArbreAVL inserer(ArbreAVL a, Comparable x){
    if (a == null)
        return new ArbreAVL(x);
    if (a.contenu.compareTo(x)>0)
        a.filsG= inserer(a.filsG,x);
    else 
        a.filsD= inserer(a.filsD,x);
    return equilibrer(a);    
}
public static ArbreAVL supprimer(ArbreAVL a, Comparable x){
   if(a==null)return a;
    if(a.contenu.compareTo(x)==0)
        return supprimerRacine(a);
    if(a.contenu.compareTo(x) > 0)
        a.filsG=supprimer(a.filsG,x);
    else
        a.filsD=supprimer(a.filsD,x);
    return equilibrer(a);
}

public static boolean chercher(ArbreAVL a , Comparable x){
    if(a== null)
         return false;
        if(a.contenu.compareTo(x)==0) 
         return true;
        if(a.contenu.compareTo(x) < 0)
          return chercher(a.filsD, x);

        return chercher(a.filsG, x);
}

private static ArbreAVL supprimerRacine(ArbreAVL a){
  if(a.filsG==null)
      return a.filsD;
    if(a.filsD==null) 
        return a.filsG;
    
    ArbreAVL r1 = a.filsG;
    ArbreAVL pere= a;
    //recherchedu plus grand élémentdu sous arbregauche
    while(r1.filsD != null) { 
        pere= r1;
        r1 = r1.filsD; 
    }
    a.contenu=r1.contenu;
    if(pere == a) 
        pere.filsG=r1.filsG;
    else 
        pere.filsD=r1.filsG;
        return a;
}

}









