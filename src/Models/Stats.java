package Models;

public class Stats {
    String name;
    float totalAvg;
    float oneAvg;
    float twoAvg;
    float threeAvg;
    float trueAvg;

    public Stats(String name, float totalAvg, float oneAvg, float twoAvg, float threeAvg, float trueAvg) {
        this.name = name;
        this.totalAvg = totalAvg;
        this.oneAvg = oneAvg;
        this.twoAvg = twoAvg;
        this.threeAvg = threeAvg;
        this.trueAvg = trueAvg;
    }

    @Override
    public String toString() {
        return  name + "       " + (int)totalAvg + "       " + (int)oneAvg + "       " + (int)twoAvg + "       " + (int)threeAvg + "       " + (int)trueAvg;
    }
}
