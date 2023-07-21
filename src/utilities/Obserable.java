package utilities;

import java.util.ArrayList;

public class Obserable {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void register(Observer o){
        observers.add(o);
    }
    public void unRegister(Observer o){
        observers.remove(o);
    }
    public void notifyObserves(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
