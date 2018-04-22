package sample;

import javax.management.timer.Timer;
import javax.swing.*;
import java.awt.*;

public class SIpanel extends JPanel {
        private SIpanel panel;
        private Timer timer;
        private int score, invaderPace, pulseRate, mysteryCount, distanceToEdge;
        private ArrayList<SIthing> cast;
        private ArrayList<SIinvader> invaders, dead;
        private ArrayList<SImissile> missileBase, missileInvader;
        private SIinvader[] bottomRow;
        private SIbase base;
        private Dimension panelDimension;
        private SImystery mysteryShip;
        private boolean gameOver, left, right, mysteryDirection, space, waveDirection;
        private boolean runningTimer;
        private Music sound;


        private void pulse() {
            pace();
            processInputs();
            if (gameOver) gameOver();
            repaint();
        }
        private void pace() {
//      IF invaders still live
            if (!invaders.isEmpty()) {
                invaderPace++;

//          Switch back manager
                if (distanceToEdge <= 10) {
                    switchBack();
                    pulseRate = (pulseRate >= 16) ? (int) (pulseRate*(0.8)) : pulseRate;
                    waveDirection = !waveDirection;
                    distanceToEdge = calculateDistanceToEdge();
                }

//              Move invaders left/right
                else if (invaderPace >= pulseRate) {
                    invaderPace = 0;
                    distanceToEdge = calculateDistanceToEdge();
                    moveAI();
                    invadersFire();
                    if (!dead.isEmpty()) removeDead();
                    if (mysteryCount < 1)   tryInitMysteryShip();
                }
//      All invaders are kill, create new wave
            } else if (missileBase.isEmpty() && missileInvader.isEmpty() && !cast.contains(mysteryShip)) {
//          System.out.println("New Wave!");
                newWave();
            }
//      Every pace
            if (!missileBase.isEmpty()) moveMissileBase();
//      Every two paces
            if (invaderPace % 2 == 0)   {
                if (!missileInvader.isEmpty()) moveMissileInvader();
                if (mysteryCount > 0)   moveMysteryShip();
            }
        }
        private void processInputs() {
            if (left)   move(left);
            if (right)  move(!right);
            if (space)  fireMissile(base, true);
        }
        protected void fireMissile(SIship ship, boolean isBase) {
            if(isBase && missileBase.isEmpty()) {
                base.playSound();
                SImissile m = new SImissile(ship.getX()+(ship.getWidth()/2), ship.getY()-(ship.getHeight()/4));
                missileBase.add(m);
                cast.add(m);
            } else if (!isBase && missileInvader.size()<3) {
                base.playSound();
                SImissile m = new SImissile(ship.getX()+(ship.getWidth()/2), ship.getY()+(ship.getHeight()/4));
                missileInvader.add(m);
                cast.add(m);
            }
        }
        private void newWave() {
            pulseRate = 50;
            int defaultY=60, defaultX=120, defaultWidth=30, defaultHeight=24;
            for(int i=0; i<5; i++) {
                for(int j=0; j<10; j++) {
                    if (i<1)    invaders.add(new SItop((j*defaultWidth)+defaultX, (i*defaultHeight)+defaultY, defaultWidth, defaultHeight));
                    else if (i<3)   invaders.add(new SImiddle((j*defaultWidth)+defaultX, (i*defaultHeight)+defaultY, defaultWidth, defaultHeight));
                    else if (i<5)   invaders.add(new SIbottom((j*defaultWidth)+defaultX, (i*defaultHeight)+defaultY, defaultWidth, defaultHeight));
                }
            }
            for (SIinvader s: invaders) {
                cast.add(s);
            }
            if (!cast.contains(base)) {
                cast.add(base);
            }
            bottomRow = getBottomRow();
        }
        private void tryInitMysteryShip() {
            Random rand = new Random();
            int x=rand.nextInt(1000);
            if (x<=3) {
                mysteryCount = 1;
                if (rand.nextBoolean()) {
                    mysteryDirection = true;
                }
                if (mysteryDirection) {
                    mysteryShip = new SImystery(0, 60, 36, 18);
                } else {
                    mysteryShip = new SImystery(480, 60, 36, 18);
                }
                cast.add(mysteryShip);
            }
        }
        private void moveMysteryShip() {
            int distance = 0;
            if (mysteryDirection) {
                mysteryShip.moveRight(5);
                distance = getWidth() - mysteryShip.getX();
            } else {
                mysteryShip.moveLeft(5);
                distance = 30+mysteryShip.getX()-mysteryShip.getWidth();
            }
            if (distance <= 5) {
                dead.add(mysteryShip);
                mysteryShip = null;
                mysteryCount = 0;
            }
        }
        private void removeDead() {
            @SuppressWarnings("unchecked")
            ArrayList<SIinvader> temp = (ArrayList<SIinvader>) dead.clone();
            dead.clear();
            for (SIinvader s : temp) {
                invaders.remove(s);
                cast.remove(s);
            }
            bottomRow = getBottomRow();
        }
        private void invadersFire() {
            int[] p = new int[bottomRow.length];
            for (int i=0; i<p.length; i++) {
                for (int j=0; j<p.length; j++) {
                    p[j] = j;
                }
                Random rand = new Random();
                int a=rand.nextInt(101);
                if (a>=20) {
                    int b=rand.nextInt(p.length);
                    fireMissile(bottomRow[b], false);
                }
            }
        }
        private int calculateDistanceToEdge() {
            int distance = 0;
            SIinvader[] outliers = getOutliers();
            if (waveDirection) {
                distance = getWidth() - outliers[0].getX()-outliers[0].getWidth();
            } else {
                distance = outliers[1].getX();
            }
            return distance;
        }
        private SIinvader[] getOutliers() {
            SIinvader leftMost = invaders.get(0), rightMost = invaders.get(0);
            for (SIinvader s : invaders) {
                if (s.getX() < leftMost.getX()) {
                    leftMost = s;
                }
                if (s.getX() > rightMost.getX()) {
                    rightMost = s;
                }
            }
            return new SIinvader[] {    rightMost,  leftMost    };
        }
        private SIinvader[] getBottomRow() {
            SIinvader[] x = new SIinvader[(invaders.size()>10)?10:invaders.size()];
            for (int i=0; i<x.length; i++) {
                x[i] = invaders.get(i);
                for (SIinvader s:invaders) {
                    if (s.getX() == x[i].getX()) {
                        if (s.getY() > x[i].getY()) {
                            x[i] = s;
                        }
                    }
                }
            }
            return x;
        }
        private void move(boolean b) {
            int defaultX = 5;
            if (b) base.moveLeft(defaultX);
            else base.moveRight(defaultX);
        }
        private void moveAI() {
            for(SIinvader s : invaders) {
                s.changeImage();
                int defaultX = 5;
                if (waveDirection) s.moveRight(defaultX);
                else s.moveLeft(defaultX);
            }
        }
        private void moveMissileBase() {
            if (invaders.isEmpty()) return;
            int movement = -5, bound = 0;
            SImissile missile = missileBase.get(0);
            missile.moveDown(movement);
            SIinvader lowestInvader = getLowestInvader();
            if (missile.getY() < (lowestInvader.getY() + lowestInvader.getHeight())) {
                for (SIinvader s:bottomRow) {
                    if (checkCollision(missile, s)) {
                        s.setHit();
                        dead.add(s);
                        cast.remove(missile);
                        missileBase.clear();
                        score += s.value;
                        return;
                    }
                }
                if (mysteryCount > 0) {
                    if (checkCollision(missile, mysteryShip)) {
                        mysteryShip.setHit();
                        dead.add(mysteryShip);
                        cast.remove(missile);
                        missileBase.clear();
                        score += mysteryShip.value;
                        return;
                    }
                }
                if (missile.getY() < bound) {
                    missileBase.remove(missile);
                    cast.remove(missile);
                }
            }
        }
        private SIinvader getLowestInvader() {
            SIinvader lowest = bottomRow[0];
            for (SIinvader invader : bottomRow) {
                if (invader.getY() > lowest.getY()) {
                    lowest = invader;
                }
            }
            return lowest;
        }
        private void moveMissileInvader() {
            int movement = 5, bound = (int) panelDimension.getHeight();
            for (SImissile missile : missileInvader) {
                missile.moveDown(movement);
                if(missile.getY() >= base.getY()) {
                    if (checkCollision(missile, base)) {
                        base.setHit();
                        gameOver = true;;
                        missileInvader.remove(missile);
                        cast.remove(missile);
                        return;
                    } else if (missile.getY() >= bound-25) {
                        missileInvader.remove(missile);
                        cast.remove(missile);
                        return;
                    }
                }
            }
        }
        private boolean checkCollision(SIthing missile, SIthing ship) {
            Rectangle2D rect1 = new Rectangle2D.Double(
                    missile.getX(),
                    missile.getY(),
                    missile.getWidth(),
                    missile.getHeight()
            );
            Rectangle2D rect2 = new Rectangle2D.Double(
                    ship.getX(),
                    ship.getY(),
                    ship.getWidth(),
                    ship.getHeight()
            );
            return rect1.intersects(rect2);
        }
        private void switchBack() {
            int defaultY = 12;
            for (SIinvader s : invaders) {
                if (s.getY() > getHeight()) {
                    gameOver = true;
                    return;
                }
                s.moveDown(defaultY);
            }
        }
        private void gameOver() {
            pause(true);
            SI.setGameOverLabelVisibile(true);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            Font font = new Font("Arial", 0, 20);
            setFont(font);
            String score = "Score: "+this.score;
            Rectangle2D rect = font.getStringBounds(score, g2.getFontRenderContext());
            int screenWidth = 0;
            try { screenWidth = (int) panelDimension.getWidth(); }
            catch (NullPointerException e) {}
            g2.setColor(Color.GREEN);
            g2.drawString(score, (int) (screenWidth - (10 + rect.getWidth())), 20);
            for(SIthing a:cast) {
                a.paint(g);
            }
        }
        public SIpanel() {
            super();
            setBackground(Color.BLACK);
            cast = new ArrayList<SIthing>();
            missileBase = new ArrayList<SImissile>();
            score = invaderPace = mysteryCount = pulseRate = 0;
            sound = new Music("AmbientMusic.wav");
            panel = this;

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT   : left = true; break;
                        case KeyEvent.VK_RIGHT  : right = true; break;
                        case KeyEvent.VK_SPACE  : space = true; break;
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT   : left = false; break;
                        case KeyEvent.VK_RIGHT  : right = false; break;
                        case KeyEvent.VK_SPACE  : space = false; break;
                    }
                }
            });


            setFocusable(true);

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pulse();
                }
            });
        }
        public void reset() {
            SI.setGameOverLabelVisibile(false);
            score = invaderPace = mysteryCount = 0;
            pulseRate = 50;
            cast = new ArrayList<SIthing>();
            invaders = new ArrayList<SIinvader>();
            dead = new ArrayList<SIinvader>();
            missileBase = new ArrayList<SImissile>();
            missileInvader = new ArrayList<SImissile>();
            base = new SIbase(230, 370, 26, 20);
            waveDirection = true;
            gameOver = false;
            sound.stop();
            sound.loop();
            panelDimension = SI.getFrameDimensions();
            bottomRow = getBottomRow();


            newWave();

            timer.start();
            runningTimer=true;
        }
        public SIpanel getPanel() {
            return this.panel;
        }
        public void pause(boolean paused) {
            if (paused) timer.stop();
            else timer.start();
        }
    }
}
