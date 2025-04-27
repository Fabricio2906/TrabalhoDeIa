package util;

import models.Robot;
import models.TypeOfPosition;

import java.util.*;

public class GeneralPurposeClass {

    public class Cell {
        public String name;
        public TypeOfPosition type;

        public Cell(String name, TypeOfPosition type) {
            this.name = name;
            this.type = type;
        }
    }

    public Cell[][] matriz = new Cell[10][10];
    private Random random = new Random();

    public void gerarMatrizAleatoria() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                matriz[i][j] = new Cell("I{" + i + "}J{" + j + "}", TypeOfPosition.Normal);

        gerarTipoAleatorio(5, TypeOfPosition.GainEnergy5);
        gerarTipoAleatorio(3, TypeOfPosition.GainEnergy10);
        gerarTipoAleatorio(random.nextInt(20) + 15, TypeOfPosition.Obscure);
    }

    private void gerarTipoAleatorio(int quantidade, TypeOfPosition tipo) {
        int count = 0;
        while (count < quantidade) {
            int i = random.nextInt(10);
            int j = random.nextInt(10);
            if ((i == 0 && j == 0) || (i == 9 && j == 9))
                continue;

            if (matriz[i][j].type == TypeOfPosition.Normal) {
                matriz[i][j].type = tipo;
                count++;
            }
        }
    }

    public void printMatriz() {
        System.out.println("\nMatriz:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (matriz[i][j].type) {
                    case Normal -> System.out.print(" . ");
                    case Obscure -> System.out.print(" X ");
                    case GainEnergy5 -> System.out.print(" + ");
                    case GainEnergy10 -> System.out.print("++ ");
                    default -> System.out.print(" ? ");
                }
            }
            System.out.println();
        }
    }

    public int moverRobo(Robot robo) {
        int x = robo.getX();
        int y = robo.getY();
        int energia = robo.getEnergia();

        List<String> caminho = new ArrayList<>();
        caminho.add("(" + x + "," + y + ")");

        while (x != 9 || y != 9) {
            boolean moveu = false;

            if (y + 1 < 10 && podeAndar(matriz[x][y + 1].type)) {
                y++;
                moveu = true;
            } else if (x + 1 < 10 && podeAndar(matriz[x + 1][y].type)) {
                x++;
                moveu = true;
            }

            if (!moveu) {
                System.out.println(" Robô preso em (" + x + "," + y + ")");
                break;
            }

            caminho.add("(" + x + "," + y + ")");
            TypeOfPosition tipo = matriz[x][y].type;
            energia -= custo(tipo);
            robo.setX(x);
            robo.setY(y);
            robo.setEnergia(energia);

            System.out.printf("  Moveu para (%d,%d) - Tipo: %s - Energia: %d%n", x, y, tipo, energia);

            if (energia <= 0) {
                System.out.println(" Energia esgotada!");
                break;
            }
        }

        System.out.println("\n Caminho percorrido:");
        System.out.println(String.join(" -> ", caminho));

        return energia;
    }

    private boolean podeAndar(TypeOfPosition tipo) {
        return tipo != TypeOfPosition.Obscure && tipo != TypeOfPosition.NotIterablePosition;
    }

    private int custo(TypeOfPosition tipo) {
        return switch (tipo) {
            case Normal -> 1;
            case GainEnergy5 -> -5;
            case GainEnergy10 -> -10;
            default -> 5;
        };
    }
}
