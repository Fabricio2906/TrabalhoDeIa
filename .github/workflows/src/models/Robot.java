package models;

public class Robot {
    private String nome;
    private int energia;
    private int x;
    private int y;

    public Robot(String nome, int energia, int x, int y) {
        this.nome = nome;
        this.energia = energia;
        this.x = x;
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public int getX() {
        return x;
    }

    public int getEnergia() {
        return energia;
    }

    public int getY() {
        return y;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
