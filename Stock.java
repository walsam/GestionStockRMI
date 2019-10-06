import Livreur.InterfaceLivreur;
import Producteur.InterfaceProducteur;

import java.rmi.Naming;
import java.util.Scanner;

public class Stock {
    public static void main(String[] args)  {
        try {
            int i =3;
            Scanner keyboard = new Scanner(System.in);
            InterfaceProducteur p = (InterfaceProducteur) Naming.lookup("rmi://localhost:1900/Production");
            InterfaceLivreur l = (InterfaceLivreur) Naming.lookup("rmi://localhost:1901/Livraison");
            System.out.println("Clique 1 pour la production");
            System.out.println("Clique 2 pour la livraison");
            while (i != 0) {
                System.out.println("production -1- ou livraison -2- ?");
                i = keyboard.nextInt();
                if (i == 1) {
                    Scanner keyboard1 = new Scanner(System.in);
                    System.out.println("Combien ?");
                    int n = keyboard1.nextInt();
                    System.out.println(p.deposer(n));
                }
                if (i == 2) {
                    Scanner keyboard1 = new Scanner(System.in);
                    System.out.println("Combien ?");
                    int n = keyboard1.nextInt();
                    System.out.println(l.livrer(n));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
