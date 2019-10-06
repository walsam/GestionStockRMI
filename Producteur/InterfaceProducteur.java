package Producteur;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceProducteur extends Remote {
    String deposer(int n) throws RemoteException, MalformedURLException, NotBoundException;
    void notify(int q) throws RemoteException;


}
