public class Set
{
    private Card one;
    private Card two;
    private Card three;
    public Set(Card CardOne, Card CardTwo, Card CardThree)
    {
        this.one = CardOne;
        this.two = CardTwo;
        this.three = CardThree;
    }

    public Card getOne()
    {
        return one;
    }

    public Card getTwo()
    {
        return two;
    }

    public Card getThree()
    {
        return three;
    }
}
