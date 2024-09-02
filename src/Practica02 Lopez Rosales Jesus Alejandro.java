import javax.swing.*;
import java.awt.*;

class PaisajeEspacial extends JFrame {
    private JPanel panel;

    public PaisajeEspacial() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setResizable(false);
        setTitle("Paisaje Espacial");
        panel = new JPanel();
        setContentPane(panel);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fondo del espacio
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);

        // Estrellas (círculos pequeños blancos)
        g.setColor(Color.white);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            g.fillOval(x, y, 3, 3);
        }

        // Trayectoria de un meteorito
        float[] dashPattern = {21, 9, 3, 8};
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(500, 100, 700, 200);

        // Meteorito al final de la trayectoria, con un cuadrado
        g.setColor(Color.CYAN);
        g.fillRect(700, 200, 20, 20);

        // Sol en la esquina superior izquierda
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 200, 200);

        // Planeta con un anillo hecho con un arco en medio
        g.setColor(Color.darkGray);
        g.fillOval(400, 200, 100, 100);

        // Arco incompleto para simular un anillo
        g.setColor(Color.PINK);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawArc(390, 220, 120, 50, 180, 180);

        // Nave espacial
        // Cuerpo de la nave (rectángulo)
        g.setColor(Color.BLUE);
        g.fillRect(400, 400, 100, 50);

        // Ventana de la nave (óvalo)
        g.setColor(Color.CYAN);
        g.fillOval(440, 410, 20, 20);

        // Alas de la nave (triángulos)
        g.setColor(Color.ORANGE);
        int[] xPoints = {350, 400, 400};
        int[] yPoints = {425, 400, 450};
        g.fillPolygon(xPoints, yPoints, 3);

        xPoints = new int[]{500, 550, 500};
        yPoints = new int[]{400, 425, 450};
        g.fillPolygon(xPoints, yPoints, 3);

        // Planeta marte
        g.setColor(Color.RED);
        g.fillOval(200, 400, 100, 100);

        // Asteroides
        g.setColor(Color.GRAY);
        g.fillOval(700, 100, 20, 20);
        g.fillOval(300, 200, 20, 20);
        g.fillOval(500, 300, 20, 20);
        g.fillOval(300, 400, 20, 20);
        g.fillOval(600, 500, 20, 20);


        // Satelite con cuadrado y antena en la pos 700
        g.setColor(Color.WHITE);
        g.fillRect(700, 500, 20, 20);
        g.setColor(Color.GRAY);
        g.fillRect(710, 510, 5, 5);
        g.drawLine(712, 510, 712, 520);

        // Black hole con arco
        g.setColor(Color.MAGENTA);
        g.fillOval(100, 500, 50, 50);
        g.setColor(Color.WHITE);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawArc(100, 500, 50, 50, 0, 360);

        // Ultimo planeta del lado derecho
        g.setColor(Color.GREEN);
        g.fillOval(600, 350, 100, 100);
    }

    public static void main(String[] args) {
        PaisajeEspacial ventana = new PaisajeEspacial();
        ventana.setVisible(true);
    }
}
