import models.Robot;
import util.GeneralPurposeClass;

public class Main {
    public static void main(String[] args) {
        GeneralPurposeClass g = new GeneralPurposeClass();

        Robot robo = new Robot("Robocop", 50, 0, 0);
        System.out.printf(" Robô %s criado com energia %d na posição (%d,%d)%n", robo.getNome(), robo.getEnergia(),
                robo.getX(), robo.getY());

        g.gerarMatrizAleatoria();
        g.printMatriz();

        int energiaFinal = g.moverRobo(robo);
        System.out.println("\nEnergia final do robô: " + energiaFinal);
    }
}
