package Models;

import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Shoots> position;

    public Player(String name, ArrayList<Shoots> position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Shoots> getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return  name + ":" + position;
    }
}