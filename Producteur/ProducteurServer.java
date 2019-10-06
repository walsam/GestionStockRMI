package Producteur;

import Livreur.LivreurServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import Livreur.InterfaceLivreur;

public class ProducteurServer extends UnicastRemoteObject implements InterfaceProducteur {

    //stockage actuel
    int s = 50;
    //capacité max
    int m = 100;
    protected ProducteurServer() throws RemoteException {
        super();
    }

    @Override
    public String deposer(int n) throws RemoteException, MalformedURLException, NotBoundException {
        if (s+n<m){
            this.s = this.s + n;
            System.out.println("J'ai deposé : "+ n + " packages !");
            System.out.println("local stock var = "+ s);
            InterfaceLivreur l = (InterfaceLivreur) Naming.lookup("rmi://localhost:1901/Livraison");
            l.notify(s);
            return "le stock est: "+s+" apres la production";
        } else return "Le stock est plein";

    }

    @Override
    public void notify(int s) throws RemoteException {
        this.s = s;
    }

    public static void main(String[] arg) throws RemoteException {
        //publication de l'interface dans l'annuaire RMIRegistry

        try{
            LocateRegistry.createRegistry(1900);
            Naming.rebind("rmi://localhost:1900/Production", new ProducteurServer());
            System.out.println("Serveur de Producteur pret !");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
