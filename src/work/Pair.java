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