import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

class PaisajeAlien extends JFrame {
    private JPanel panel;

    public PaisajeAlien() {
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
        Random random = new Random();

        // Fondo del espacio mas black que blue
        GradientPaint gpSpace = new GradientPaint(0, 0, Color.DARK_GRAY, 0, 600, Color.BLACK);
        g2d.setPaint(gpSpace);
        g.fillRect(0, 0, 800, 600);

        // Muy pocas lineas rectas muy finas y pequeñas para simular cometas
        int numberOfComets = 10;
        int minDistance = 100;  // Distancia mínima entre cometas
        ArrayList<Point> cometPositions = new ArrayList<>();

        // Configuración de las líneas de los cometas
        float[] dashPattern = {1, 1};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
        g2d.setColor(Color.CYAN);

        for (int i = 0; i < numberOfComets; i++) {
            int x1, y1, x2, y2;
            boolean validPosition;

            do {
                validPosition = true;
                x1 = random.nextInt(800);
                y1 = random.nextInt(600);
                x2 = x1 + (int) (random.nextInt(20) + 10);
                y2 = y1 + (int) (random.nextInt(20) + 10);

                // Verificar la distancia a otros cometas
                for (Point p : cometPositions) {
                    if (Point.distance(p.x, p.y, x1, y1) < minDistance ||
                            Point.distance(p.x, p.y, x2, y2) < minDistance) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition);

            // Almacenar la posición del cometa
            cometPositions.add(new Point(x1, y1));

            // Dibujar el cometa
            g2d.drawLine(x1, y1, x2, y2);
            int[] vx = {x2, x2 - 5, x2 + 5};
            int[] vy = {y2, y2 + 10, y2 + 10};
            g.fillPolygon(vx, vy, 3);
        }

        // Estrellas (círculos pequeños blancos)
        g.setColor(Color.white);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            g.fillOval(x, y, 3, 3);
        }

        // Naves espaciales
        int numberOfShips = 5;
        minDistance = 100;  // Distancia mínima entre naves
        ArrayList<Point> shipPositions = new ArrayList<>();

        for (int i = 0; i < numberOfShips; i++) {
            int x, y;
            boolean validPosition;

            do {
                validPosition = true;
                x = random.nextInt(800 - 50);  // Ajustar el rango de x
                y = random.nextInt(600 - 20);  // Ajustar el rango de y

                // Verificar la distancia a otras naves
                for (Point p : shipPositions) {
                    if (Point.distance(p.x, p.y, x, y) < minDistance) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition);

            // Almacenar la posición de la nave
            shipPositions.add(new Point(x, y));

            // Dibujar la nave
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(x, y, 50, 20);
            g.setColor(Color.CYAN);
            g.fillOval(x + 10, y + 5, 10, 10);

            // Dibujar un cuadrado en la parte de abajo de la nave
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x + 20, y + 20, 10, 5);
        }

        // Sol en medio con GradientPaint
        GradientPaint gp = new GradientPaint(400, 300, Color.YELLOW, 500, 400, Color.RED);
        g2d.setPaint(gp);
        g.fillOval(250, 150, 300, 300);

        // Lista para almacenar las posiciones y tamaños de los planetas
        ArrayList<Point2D> planetPositions = new ArrayList<>();
        ArrayList<Integer> planetSizes = new ArrayList<>();

        // Generar 10 planetas aleatorios
        for (int i = 0; i < 10; i++) {
            int x, y, size;
            boolean tooClose;

            // Asegurarse de que los planetas no estén encima del sol ni pegados entre sí
            do {
                tooClose = false;
                x = random.nextInt(750);  // Coordenadas X aleatorias
                y = random.nextInt(350);  // Coordenadas Y aleatorias
                size = random.nextInt(90) + 20;  // Tamaño aleatorio entre 20 y 110

                // Verificar que no esté en el área del sol
                if ((x + size > 250 && x < 550) && (y + size > 150 && y < 450)) {
                    tooClose = true;
                } else {
                    // Verificar que no esté demasiado cerca de otros planetas
                    for (int j = 0; j < planetPositions.size(); j++) {
                        Point2D planet = planetPositions.get(j);
                        int existingSize = planetSizes.get(j);
                        double distance = planet.distance(x, y);
                        if (distance < (existingSize + size) * 1.5) {  // 1.5 veces el tamaño sumado para separación
                            tooClose = true;
                            break;
                        }
                    }
                }
            } while (tooClose);

            // Almacenar la posición y el tamaño del planeta
            planetPositions.add(new Point2D.Double(x, y));
            planetSizes.add(size);

            // Colores aleatorios para el degradado
            Color color1 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Color color2 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            // Tipo aleatorio de degradado (lineal o radial)
            if (random.nextBoolean()) {
                // Degradado lineal
                GradientPaint gpPlanet = new GradientPaint(x, y, color1, x + size, y + size, color2);
                g2d.setPaint(gpPlanet);
            } else {
                // Degradado radial
                RadialGradientPaint rgp = new RadialGradientPaint(
                        new Point(x + size / 2, y + size / 2),
                        size / 2,
                        new float[]{0f, 1f},
                        new Color[]{color1, color2}
                );
                g2d.setPaint(rgp);
            }

            // Dibujar el planeta
            g2d.fillOval(x, y, size, size);

            // Añadir anillo aleatoriamente con un 25% de probabilidad
            if (random.nextInt(4) == 0) {
                // Dibujar un anillo proporcional al tamaño del planeta
                Color ringColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                g.setColor(ringColor);
                ((Graphics2D) g).setStroke(new BasicStroke(2));
                g.drawArc(x - size / 4, y + size / 4, size + size / 2, size / 3, 180, 180);
            }
        }

        // Rectangulo en toda la parte de abajo para simular un suelo de un planeta con GradientPaint
        GradientPaint gpGround = new GradientPaint(0, 600, Color.DARK_GRAY, 0, 500, Color.GRAY);
        g2d.setPaint(gpGround);
        g.fillRect(0, 500, 800, 100);

        // Texturas en el suelo con ovalos para simular rocas y crateres
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 800);
            int y = 500 + (int) (Math.random() * 100);
            int size = (int) (Math.random() * 10) + 5;
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x, y, size, size);
        }

        // Imagen de Xhenomorph en el suelo de 300 x 400
        ImageIcon xhenomorph = new ImageIcon("src/img/xhenomorph.png");
        g.drawImage(xhenomorph.getImage(), 500, 400, 200, 200, this);

        // Xenomorphs 2 también en el suelo
        ImageIcon xhenomorph2 = new ImageIcon("src/img/xhenomorph2.png");
        g.drawImage(xhenomorph2.getImage(), 100, 400, 200, 200, this);

    }

    public static void main(String[] args) {
        PaisajeAlien ventana = new PaisajeAlien();
        ventana.setVisible(true);
    }
}
