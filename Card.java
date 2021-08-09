public class Card
{
    /**
     * Have a number of sets on a board and know which sets those are
     * have a way to go through those sets and highlight each one
     */
    private String Shape;
    private int Num;
    private String Color;
    private String Fill;
    private boolean isHighlighted;
    private String[] Shapes = new String[]{"P", "D", "S"};
    private int[] Nums = new int[]{1, 2, 3};
    private String[] Colors = new String[]{"R", "P", "G"};
    private String[] Fills = new String[]{"B", "D", "S"};

    public Card(String Shape, int Num, String Color, String Fill)
    {
        this.Shape = Shape;
        this.Num = Num;
        this.Color = Color; 
        this.Fill = Fill;
        this.isHighlighted = false;
    }

    public String getShape()
    {
        return this.Shape;
    }

    public int getNum()
    {
        return this.Num;
    }

    public String getColor()
    {
        return this.Color;
    } 

    public String getFill()
    {
        return this.Fill;
    }

    public boolean Highlight()
    {
        return this.isHighlighted;
    }

    public void setShape(String Shape)
    {
        this.Shape = Shape;
    }

    public void setNum(int Num)
    {
        this.Num = Num;
    }

    public void setColor(String Color)
    {
        this.Color = Color;
    }

    public void setFill(String Fill)
    {
        this.Fill = Fill;
    }

    public void setHighlight(boolean highlight)
    {
        this.isHighlighted = highlight;
    }

    public String getCard()
    {
        return "" + this.Color + this.Fill + this.Shape;
    }

    public String toString()
    {
        return "" + this.getNum() + this.getCard();
    }
}

