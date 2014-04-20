package work;

public class Pair
{
    private final int key;
    private final int value;

    public Pair(int aKey, int aValue)
    {
        key   = aKey;
        value = aValue;
    }

	public int key()   { return key; }
    public int value() { return value; }
}

class FitnessPair implements Comparable<FitnessPair> {
    int fitness;
    Chromozome chromozome;

    public FitnessPair(int fitness, Chromozome chromozome) {
        this.fitness = fitness;
        this.chromozome = chromozome;
    }

    @Override
    public int compareTo(FitnessPair o) {
        return fitness > o.fitness ? -1 : fitness < o.fitness ? 1 : 0;
    }
    
}