package voronoi;

import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.io.File;

public class Voronoi extends javax.swing.JFrame {

    static double p = 3;
    static BufferedImage I;
    static int px[], py[], color[], cells = 0, size = 500;

    public Voronoi() {
        super();
        new Diseño.design(this, "Logo.jpg", "Diagrama voronoi");
        setLocationRelativeTo(null);
        setBounds(0, 0, size, size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int n = 0;
        Random rand = new Random();
        I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        String tmp = JOptionPane.showInputDialog(null, "¿Cuantos puntos ingresará?\n(Si no ingresas nada se ingresaran 2 puntos unicamente)", "");
        cells = Integer.parseInt(!tmp.equals("") ? tmp : "2");
        JOptionPane.showMessageDialog(null, "Se ingresaran " + cells + " puntos", "aviso", JOptionPane.DEFAULT_OPTION, null);
        px = new int[cells];
        py = new int[cells];
        color = new int[cells];
        
        for (int i = 0; i < cells; i++) {
            tmp = JOptionPane.showInputDialog(null, "Ingresa la coordenada x del punto " + (i+1) + " en un rango de 0 a " + size + "\n(Si no ingresas nada se tomara un numero al azar)","");
            px[i] = !tmp.equals("") ? Integer.parseInt(tmp) : rand.nextInt(size);
            tmp = JOptionPane.showInputDialog(null, "Ingresa la coordenada y del punto " + (i+1) + " en un rango de 0 a " + size + "\n(Si no ingresas nada se tomara un numero al azar)","");
            py[i] = !tmp.equals("") ? Integer.parseInt(tmp) : rand.nextInt(size);
            color[i] = rand.nextInt(16777215);

        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                n = 0;
                for (int i = 0; i < cells; i++)
                    if (distance(px[i], x, py[i], y) < distance(px[n], x, py[n], y)) 
                        n = i;
                I.setRGB(x, y, color[n]);

            }
        }

        Graphics2D g = I.createGraphics();
        g.setColor(Color.BLACK);
        for (int i = 0; i < cells; i++) {
            g.fill(new Ellipse2D.Double(px[i] - 2.5, py[i] - 2.5, 5, 5));
        }

        try {
            ImageIO.write(I, "png", new File("voronoi.png"));
        } catch (IOException e) {

        }

    }

    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    static double distance(int x1, int x2, int y1, int y2) {
        double d;
        d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return d;
    }

    public static void main(String[] args) {
        new Voronoi().setVisible(true);
    }
}
