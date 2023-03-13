public class Block
{
    private int wert;
    private boolean fest;
    public Block(int pwert, boolean pfest)
    {
        wert = pwert;
        fest = pfest;
    }

    public void reset()
    {
        wert = 0;
    }

    public int gibWert()
    {
        return wert;
    }

    public boolean istFest()
    {
        return fest;
    }

    public void nächster()
    {
        if(wert<9)
        {
            wert++;
        }
        else wert =-1;
    }
}
