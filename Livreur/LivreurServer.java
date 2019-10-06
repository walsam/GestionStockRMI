package Livreur;

import Producteur.InterfaceProducteur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class LivreurServer extends UnicastRemoteObject implements InterfaceLivreur {

    //stockage actuel
    int s = 50;

    protected LivreurServer() throws RemoteException {
        super();
    }

    @Override
    public String livrer(int n) throws RemoteException, MalformedURLException, NotBoundException {
        if (s>n) {
            this.s = this.s - n;
            System.out.println("J'ai livré : "+ n + " packages !");
            System.out.println("local stock var = "+ s);
            InterfaceProducteur p = (InterfaceProducteur) Naming.lookup("rmi://localhost:1900/Production");
            p.notify(s);
            return "le stock (variable local de livreur) est: "+s;
        }else return "Le stock est epuisé";


    }

    @Override
    public void notify(int s) throws RemoteException {
        this.s = s;
    }

    public static void main(String[] arg) throws RemoteException {
        //publication de l'interface dans l'annuaire RMIRegistry

        try{
            LocateRegistry.createRegistry(1901);
            Naming.rebind("rmi://localhost:1901/Livraison", new LivreurServer());
            System.out.println("Serveur de livreur pret !");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
