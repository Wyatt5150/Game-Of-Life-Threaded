class Cell
{
    public boolean currVal, nextVal;
    public Cell(boolean val)
    {
        currVal = val;
        nextVal = val;
    }

    public boolean GetVal()
    {
        return currVal;
    }

    //Updates current value with queued value, inteded for end of cycle
    public void UpdateVal()
    {
        currVal = nextVal;
    }

    //Queue next value in order to retain current value for the cycle
    public void QueueNextVal(boolean val)
    {
        nextVal = val;
    }
}