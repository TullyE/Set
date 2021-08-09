import javax.swing.*;

import java.awt.geom.*;

import java.awt.Color;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
HomepageMenu.java
has the graphics code for the Homepage
Tully Eva
07/29/2021
*/

public class GameMeunue extends JPanel implements MouseListener, KeyListener
{
    private String[] Shapes = new String[]{"P", "D", "S"};
    private int[] Nums = new int[]{1, 2, 3};
    private String[] Colors = new String[]{"R", "P", "G"};
    private String[] Fills = new String[]{"B", "D", "S"};
    private int cardsHighlighted = 0;
    private HashMap<Integer, Integer[]> positionToXY = new HashMap<>();
    private int cardsToDraw = 12;
    private boolean isSet = false;
    private boolean showError = false;
    private boolean waiting = false;
    private int score = 0;
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<CardOutline> cardOutlines = new ArrayList<>();
    /**
     * Default constructor for HomepageMenu
     * create/setup all the components and add them to the screen
     */
    public GameMeunue()
    {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        
        makeDeck();
        
        int x = 21;
        int y = 31;
        for (int i = 1; i < 15+1; i += 1)
        {
            Color Black = new Color(0,0,0);
            CardOutline card = new CardOutline(x, y, 75, 150, Black);
            cardOutlines.add(card);

            x += card.getWidth() + 4;

            Integer[] add = new Integer[2];
            add[0] = x - (card.getWidth()/2) - 25;
            add[1] = y + (card.getHeight()/2) - 25;
            positionToXY.put(i, add);

            if (i % 3 == 0)
            {
                System.out.println(i);
                y += card.getHeight() + 4;
                x = 21;
            }
        }
    }

    public void drawCards(Graphics2D g2, int numberOfCards)
    {
        for(int i = 0; i < numberOfCards; i += 1)
        {
            cardOutlines.get(i).draw(g2);
        }
        int x = 21;
        int y = cardOutlines.get(13).getY();
        if(numberOfCards == 12)
        {
            System.out.println("true");
            BufferedImage card = null;
            for(int i = 0; i < 3; i += 1)
            {
                try
                {
                    card = ImageIO.read(new File("./Images/Empty.png"));
                }
                catch (IOException e) {e.printStackTrace();}
                Image dimg = card.getScaledInstance(card.getWidth() * 1, card.getHeight() * 1, Image.SCALE_SMOOTH);
                g2.drawImage(dimg, x, y, this);
                x += dimg.getWidth(this) + 4;
            }
        }
    }

    public void drawCard(Graphics2D g2, Card cardToDraw, int cardNum)
    {
        BufferedImage card = null;
        try
        {
            card = ImageIO.read(new File("./Images/" + cardToDraw.getCard() + ".png"));
        }
        catch (IOException e) {e.printStackTrace();}
        Image dimg = card.getScaledInstance(card.getWidth() * 1, card.getHeight() * 1, Image.SCALE_SMOOTH);
        if(cardToDraw.getNum() == 1)
        {
            g2.drawImage(dimg, positionToXY.get(cardNum)[0], positionToXY.get(cardNum)[1], this);
        }
        else if(cardToDraw.getNum() == 2)
        {
            g2.drawImage(dimg, positionToXY.get(cardNum)[0] + 20, positionToXY.get(cardNum)[1], this);
            g2.drawImage(dimg, positionToXY.get(cardNum)[0] - 20, positionToXY.get(cardNum)[1], this);
        }
        else if(cardToDraw.getNum() == 3)
        {
            g2.drawImage(dimg, positionToXY.get(cardNum)[0] - 50, positionToXY.get(cardNum)[1], this);
            g2.drawImage(dimg, positionToXY.get(cardNum)[0], positionToXY.get(cardNum)[1], this);
            g2.drawImage(dimg, positionToXY.get(cardNum)[0] + 50, positionToXY.get(cardNum)[1], this);
        }
    }

    /**
     * set the background aswell as the text for the slider
     * @param g the Graphics object to protect
     */
    public void paintComponent(Graphics g)
    {
        if (waiting)
        {
            try 
            {
               Thread.sleep(100);
            }
            catch (InterruptedException ex){}
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 255, 255));
        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, 500, 500);
        g2.draw(background);
        g2.fill(background);
        drawCards(g2, cardsToDraw); 

        if(deck.size() == 0)
        {
            // this.dispose();
            this.setVisible(false);
        }

        g2.setFont(new Font("Monospaced", Font.BOLD, 22));
        g2.setColor(new Color(164, 164, 163));
        g2.drawString("SCORE: " + score, 350, 450);

        // if(deck.size() < 12)
        // {
        //     cardsToDraw = deck.size();
        // }
        for(int i = 1; i < cardsToDraw + 1; i += 1)
        {
            if(i-1 >= deck.size())
            {
                break;
            }
            drawCard(g2, deck.get(i-1), i);
        }
        if(isSet)
        {
            g2.setFont(new Font("Monospaced", Font.BOLD, 22));
            g2.setColor(new Color(164, 164, 163));
            g2.drawString("THERE IS A SET! ", 21, 450);
        }
        if (showError)
        {
            BufferedImage card = null;
            try
                {
                    card = ImageIO.read(new File("./Images/Error.png"));
                }
            catch (IOException e) {e.printStackTrace();}
            Image dimg = card.getScaledInstance(card.getWidth() * 1, card.getHeight() * 1, Image.SCALE_SMOOTH);
            g2.drawImage(dimg, 0, 0, this);
            showError = false;
            waiting = true;
            repaint();
        }
    }

    public void makeDeck()
    {
        for(int num = 0; num < 3; num += 1)
        {
            for(int color = 0; color < 3; color += 1)
            {
                for(int shape = 0; shape < 3; shape += 1)
                {
                    for(int fill = 0; fill < 3; fill += 1)
                    {
                        deck.add(new Card(Shapes[shape], Nums[num], Colors[color], Fills[fill]));
                    }
                }
            }
        }
        Collections.shuffle(deck);
        System.out.println(deck);
        // deck.add(0, new Card(Shapes[0], Nums[0], Colors[0], Fills[0]));
        // deck.add(0, new Card(Shapes[1], Nums[1], Colors[1], Fills[1]));
        // deck.add(0, new Card(Shapes[2], Nums[2], Colors[2], Fills[2]));
    }

    public boolean checkSet(Card one, Card two, Card three)
    {
        String color1 = one.getColor();
        String color2 = two.getColor();
        String color3 = three.getColor();

        int num1 = one.getNum();
        int num2 = two.getNum();
        int num3 = three.getNum();

        String shape1 = one.getShape();
        String shape2 = two.getShape();
        String shape3 = three.getShape();

        String fill1 = one.getFill();
        String fill2 = two.getFill();
        String fill3 = three.getFill();

        int passes = 0;
        if(((num1 == num2) && (num1 == num3)) || ((num1 != num2) && (num1 != num3) && (num3 != num2)))
        {
            passes += 1;
        }
        if(checkAttribute(shape1, shape2, shape3))
        {
            passes += 1;
        }
        if(checkAttribute(color1, color2, color3))
        {
            passes += 1;
        }
        if(checkAttribute(fill1, fill2, fill3))
        {
            passes += 1;
        }
        if(passes == 4)
        {
            return true;
        }
        return false;
    }

    public boolean checkAttribute(String one, String two, String three)
    {
        if(one.equals(two) && one.equals(three))
        {
            return true;
        }
        else if (!one.equals(two) && !one.equals(three) && !two.equals(three))
        {
            return true;
        }
        return false;
    }

    public void findSet()
    {
        int goThrough;
        if(deck.size() < 12)
        {
            goThrough = deck.size();
        }
        else
        {
            goThrough = cardsToDraw;
        }
        for(int i = 0; i < goThrough; i += 1)
        {
            for (int j = i + 1; j < goThrough; j += 1)
            {
                for (int h = j + 1; h < goThrough; h += 1)
                {
                    if(checkSet(deck.get(i), deck.get(j), deck.get(h)))
                    {
                        showSet(i, j, h);
                        break;
                    }
                }
            }
        }
    }

    public boolean checkForSetOnBoard()
    {
        int goThrough;
        if(deck.size() < 12)
        {
            goThrough = deck.size();
        }
        else
        {
            goThrough = cardsToDraw;
        }
        for(int i = 0; i < goThrough; i += 1)
        {
            for (int j = i + 1; j < goThrough; j += 1)
            {
                for (int h = j + 1; h < goThrough; h += 1)
                {
                    if(checkSet(deck.get(i), deck.get(j), deck.get(h)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void showSet(int pos1, int pos2, int pos3)
    {
        for(int i = 0; i < cardOutlines.size(); i += 1)
        {
            cardOutlines.get(i).setIsHighlighted(false);
        }
        cardOutlines.get(pos1).setIsHighlighted(true);
        cardOutlines.get(pos2).setIsHighlighted(true);
        cardOutlines.get(pos3).setIsHighlighted(true);
        cardsHighlighted = 3;
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == 10)
        {
            System.out.println("enter key");
            if(cardsHighlighted == 3)
            {
                int pos1 = -1;
                int pos2 = -1;
                int pos3 = -1;
                for(int i = 0; i < cardOutlines.size(); i += 1)
                {
                    if(cardOutlines.get(i).getIsHighlighted())
                    {
                        if(pos1 == -1)
                        {
                            pos1 = i;
                        }
                        else if (pos2 == -1)
                        {
                            pos2 = i;
                        }
                        else if (pos3 == -1)
                        {
                            pos3 = i;
                        }
                    }
                }
                if(checkSet(deck.get(pos1), deck.get(pos2), deck.get(pos3)))
                {
                    score += 1;
                    deck.remove(pos3);
                    deck.remove(pos2);
                    deck.remove(pos1);
                    for(int i = 0; i < cardOutlines.size(); i += 1)
                    {
                        cardOutlines.get(i).setIsHighlighted(false);
                        cardsHighlighted = 0;
                    }
                    cardsToDraw = 12;
                    isSet = false;
                }
                else
                {
                    showError = true;
                    score -= 1;
                    repaint();
                }
            }
            repaint();
        }
        else if(e.getKeyCode() == 83)
        {
            findSet();
            repaint();
            score -= 2;
        }
        else if(e.getKeyCode() == 77)
        {
            if(checkForSetOnBoard() == false)
            {
                cardsToDraw = 15;
                repaint();
            }
            else
            {
                isSet = true;
                repaint();
                score -= 1;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        for(int i = 0; i < cardOutlines.size(); i += 1)
        {
            if (cardOutlines.get(i).isHit(e.getX(), e.getY()))
            {
                if(i > cardsToDraw -1)
                {
                    break;
                }
                if(cardOutlines.get(i).getIsHighlighted())
                {
                    cardOutlines.get(i).setIsHighlighted(false);
                    repaint();
                    cardsHighlighted -= 1;
                    break;
                }
                if(cardsHighlighted < 3)
                {
                    cardOutlines.get(i).setIsHighlighted(true);
                    repaint();
                    cardsHighlighted += 1;
                    System.out.println(i);
                    break;
                }
            }
        }
        System.out.println(e.getX());
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
