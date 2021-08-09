import java.awt.*;
import java.awt.geom.*;
/**
Rectangle.java
Tully, Cassandra
07/16/2021
*/

public class CardOutline extends Tile
{
    private boolean isHighlighted = false;
    /**
     * default constructor of a rectangle same as a tile
     * @param x coord
     * @param y coord
     * @param h height
     * @param w width
     * @param c color
     */
    public CardOutline(int x, int y, int h, int w, Color c)
    {
        super(x, y, w, h, c);
    }

    /**
     * Draws this tile using the given graphics pen.
     * @param g2 the paintbrush
     */
    public void draw(Graphics2D g2)
    {
        if(isHighlighted)
        {
            Rectangle2D.Double highlightRect = new Rectangle2D.Double(super.getX()-1, super.getY()-1, super.getWidth()+2, super.getHeight()+2);
            // g2.setColor(new Color(58,175,220));
            g2.setColor(new Color(0,0,0));
            g2.draw(highlightRect);
            g2.fill(highlightRect);
        }
        Rectangle2D.Double myRect = new Rectangle2D.Double(super.getX() + 2, super.getY()+2, super.getWidth()-4, super.getHeight()-4);
        g2.setColor(super.getColor());
        g2.draw(myRect);
        g2.setColor(new Color(255,255,255));
        g2.fill(myRect);
        
    }

    public boolean getIsHighlighted()
    {
        return isHighlighted;
    }

    public void setIsHighlighted(boolean highlight)
    {
        this.isHighlighted = highlight;
    }
}