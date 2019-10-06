package Livreur;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLivreur extends Remote {
    String livrer(int n) throws RemoteException, MalformedURLException, NotBoundException;
    void notify(int q) throws RemoteException;
}
