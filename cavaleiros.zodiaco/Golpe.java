public class Golpe {
    private String nome;
    private int fatorDano;
    
    public Golpe(String nome, int fatorDano) {
        this.nome = nome;
        this.fatorDano = fatorDano;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getFatorDano() {
        return fatorDano;
    }
}