import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatalhaTest {
    @Test
    public void iniciarBatalhaComMovimentosDeDano() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        Golpe basico = new Golpe("Ataque básico", 10);
        milo.aprenderGolpe(basico);
        milo.adicionarMovimento(new VestirArmadura(milo));
        milo.adicionarMovimento(new Golpear(milo, ares));
        Golpe forte = new Golpe("Ataque básico", 50);
        ares.aprenderGolpe(forte);
        ares.adicionarMovimento(new Golpear(ares, milo));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(0, milo.getVida(), 0.01);
        assertEquals(60, ares.getVida(), 0.01);
    }
    
    @Test
    public void iniciarBatalhaSemMovimentos() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(100, ares.getVida(), 0.01);
    }
    
    @Test
    public void iniciarBatalhaSemMovimentosDeDano() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.adicionarMovimento(new VestirArmadura(milo));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(100, ares.getVida(), 0.01);
    }
    
    /*
     * Batalha bugando:
     * 
     * @Test
    public void iniciarBatalhaSemGolpes() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ares.adicionarMovimento(new Golpear(ares, milo));
        Batalha epicBattle = new Batalha(milo, ares);
        epicBattle.iniciar();
        assertEquals(100, milo.getVida(), 0.01);
        assertEquals(100, ares.getVida(), 0.01);
    }*/
}