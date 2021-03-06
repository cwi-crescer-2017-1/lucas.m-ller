import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class ListaSainsTest {
    @Test
    public void buscarSaintExistentePorNome() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        assertEquals(milo, lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarSaintExistenteComRepeticaoDeNomes() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        SilverSaint milo2 = new SilverSaint("Milo", "Pégaso");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(milo2);
        assertEquals(milo, lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarSaintInexistentePorNome() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        assertNull(lista.buscarPorNome("Marin"));
    }
    
    @Test
    public void buscarSaintExistentePorNomeComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertNull(lista.buscarPorNome("Milo"));
    }
    
    @Test
    public void buscarPorCategoria() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.OURO);
        assertEquals(milo, subLista.get(0));
        assertEquals(afrodite, subLista.get(1));
        assertEquals(2, subLista.size());
    }
    
    @Test
    public void buscarPorCategoriaInexistente() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        ArrayList<Saint> subLista = lista.buscarPorCategoria(Categoria.PRATA);
        assertEquals(0, subLista.size());
    }
    
    @Test
    public void buscarPorCategoriaComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertEquals(0, lista.buscarPorCategoria(Categoria.OURO).size());
    }
    
    @Test
    public void buscarPorStatus() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        ArrayList<Saint> subLista = lista.buscarPorStatus(Status.VIVO);
        assertEquals(milo, subLista.get(0));
        assertEquals(afrodite, subLista.get(1));
        assertEquals(2, subLista.size());
    }
    
    @Test
    public void buscarPorStatusInexistente() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        ArrayList<Saint> subLista = lista.buscarPorStatus(Status.MORTO);
        assertEquals(0, subLista.size());
    }
    
    @Test
    public void buscarPorStatusComListaVazia() {
        ListaSaints lista = new ListaSaints();
        assertEquals(0, lista.buscarPorStatus(Status.VIVO).size());
    }
    
    @Test
    public void getSaintDeMaiorVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        assertEquals(milo, lista.getSaintMaiorVida());
    }
    
    @Test
    public void getSaintDeMaiorVidaComListaVazia() throws Exception {
        assertNull(new ListaSaints().getSaintMaiorVida());
    }
    
    @Test
    public void getSaintDeMenorVida() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        marin.perderVida(100);
        afrodite.perderVida(90);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        assertEquals(marin, lista.getSaintMenorVida());
    }
    
    @Test
    public void getSaintDeMenorVidaComListaVazia() throws Exception {
        assertNull(new ListaSaints().getSaintMenorVida());
    }
    
    @Test
    public void ordenarSaintsComListaTotalmenteDesordenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar();
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarSaintsComListaTotalmenteOrdenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(ares);
        lista.adicionar(marin);
        lista.adicionar(afrodite);
        lista.adicionar(milo);
        lista.ordenar();
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarSaintsComUmSaintNaLista() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.ordenar();
        assertEquals(milo, lista.get(0));
        assertFalse(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarSaintsComListaVazia() {
        ListaSaints lista = new ListaSaints();
        lista.ordenar();
        assertTrue(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarTipoOrdernacaoAscendenteSaintsComListaTotalmenteDesordenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar(TipoOrdenacao.ASCENDENTE);
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarTipoOrdernacaoAscendenteSaintsComListaTotalmenteOrdenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(ares);
        lista.adicionar(marin);
        lista.adicionar(afrodite);
        lista.adicionar(milo);
        lista.ordenar(TipoOrdenacao.ASCENDENTE);
        assertEquals(ares, lista.get(0));
        assertEquals(marin, lista.get(1));
        assertEquals(afrodite, lista.get(2));
        assertEquals(milo, lista.get(3));
    }
    
    @Test
    public void ordenarTipoOrdernacaoAscendenteSaintsComUmSaintNaLista() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.ordenar(TipoOrdenacao.ASCENDENTE);
        assertEquals(milo, lista.get(0));
        assertFalse(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarTipoOrdernacaoAscendenteSaintsComListaVazia() {
        ListaSaints lista = new ListaSaints();
        lista.ordenar(TipoOrdenacao.ASCENDENTE);
        assertTrue(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarTipoOrdernacaoDescendenteSaintsComListaTotalmenteOrdenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.adicionar(afrodite);
        lista.adicionar(marin);
        lista.adicionar(ares);
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertEquals(milo, lista.get(0));
        assertEquals(afrodite, lista.get(1));
        assertEquals(marin, lista.get(2));
        assertEquals(ares, lista.get(3));
    }
    
    @Test
    public void ordenarTipoOrdernacaoDescendenteSaintsComListaTotalmenteDesordenada() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        milo.perderVida(10);
        afrodite.perderVida(20);
        marin.perderVida(30);
        ares.perderVida(40);
        ListaSaints lista = new ListaSaints();
        lista.adicionar(ares);
        lista.adicionar(marin);
        lista.adicionar(afrodite);
        lista.adicionar(milo);
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertEquals(milo, lista.get(0));
        assertEquals(afrodite, lista.get(1));
        assertEquals(marin, lista.get(2));
        assertEquals(ares, lista.get(3));
    }
    
    @Test
    public void ordenarTipoOrdernacaoDescendenteSaintsComUmSaintNaLista() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        ListaSaints lista = new ListaSaints();
        lista.adicionar(milo);
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertEquals(milo, lista.get(0));
        assertFalse(lista.todos().isEmpty());
    }
    
    @Test
    public void ordenarTipoOrdernacaoDescendenteSaintsComListaVazia() {
        ListaSaints lista = new ListaSaints();
        lista.ordenar(TipoOrdenacao.DESCENDENTE);
        assertTrue(lista.todos().isEmpty());
    }
    
    @Test
    public void unirListasComUmSaint() throws Exception {
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        ListaSaints resultado = lista1.unir(lista2);
        assertEquals(2, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
        assertEquals(marin, resultado.get(1));
    }
    
    @Test
    public void unirListasComVariosSaints() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(ares);
        lista1.adicionar(milo);
        lista1.adicionar(afrodite);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        ListaSaints resultado = lista1.unir(lista2);
        assertEquals(4, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
        assertEquals(milo, resultado.get(1));
        assertEquals(afrodite, resultado.get(2));
        assertEquals(marin, resultado.get(3));
    }
    
    @Test
    public void unirListasComSaintIgual() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(ares);
        lista1.adicionar(marin);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        ListaSaints resultado = lista1.unir(lista2);
        assertEquals(3, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
        assertEquals(marin, resultado.get(1));
        assertEquals(marin, resultado.get(2));
    }
    
    @Test
    public void unirListasComNenhumSaint() throws Exception {
        ListaSaints lista1 = new ListaSaints();
        ListaSaints lista2 = new ListaSaints();
        ListaSaints resultado = lista1.unir(lista2);
        assertEquals(0, resultado.todos().size());
    }
    
    @Test
    public void diffListasComUmSaintIgual() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(new SilverSaint("Marin", "Águia"));
        ListaSaints resultado = lista1.diff(lista2);
        assertEquals(1, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
    }
    
    @Test
    public void diffListasComVariosSaintsIguais() throws Exception {
        GoldSaint milo = new GoldSaint("Milo", "Escorpião");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        lista1.adicionar(milo);
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        lista2.adicionar(afrodite);
        lista2.adicionar(milo);
        ListaSaints resultado = lista1.diff(lista2);
        assertEquals(1, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
    }
    
    @Test
    public void diffListasComNenhumSaintDiferente() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(marin);
        lista2.adicionar(ares);
        ListaSaints resultado = lista1.diff(lista2);
        assertEquals(0, resultado.todos().size());
    }
    
    @Test
    public void intersecListasComUmSaintIgual() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(ares);
        ListaSaints resultado = lista1.intersec(lista2);
        assertEquals(1, resultado.todos().size());
        assertEquals(ares, resultado.get(0));
    }
    
    @Test
    public void intersecListasComVariosSaintsIguais() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        GoldSaint afrodite = new GoldSaint("Afrodite", "Peixes");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        lista1.adicionar(afrodite);
        lista1.adicionar(ares);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(ares);
        lista2.adicionar(afrodite);
        ListaSaints resultado = lista1.intersec(lista2);
        assertEquals(2, resultado.todos().size());
        assertEquals(afrodite, resultado.get(0));
        assertEquals(ares, resultado.get(1));
    }
    
    @Test
    public void intersecListasComNenhumSaintIgual() throws Exception {
        SilverSaint marin = new SilverSaint("Marin", "Águia");
        BronzeSaint ares = new BronzeSaint("Ares", "Touro");
        ListaSaints lista1 = new ListaSaints();
        lista1.adicionar(marin);
        ListaSaints lista2 = new ListaSaints();
        lista2.adicionar(ares);
        ListaSaints resultado = lista1.intersec(lista2);
        assertEquals(0, resultado.todos().size());
    }
    
    @Test
    public void getCSVComUmSaint() throws Exception {
        ListaSaints lista = new ListaSaints();
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        june.setGenero(Genero.FEMININO);
        june.perderVida(15.5);
        lista.adicionar(june);
        
        assertEquals("June,84.5,Camaleão,BRONZE,VIVO,FEMININO,false", lista.getCSV());
    }
    
    @Test
    public void getCSVComDoisSaints() throws Exception {
        ListaSaints lista = new ListaSaints();
        BronzeSaint june = new BronzeSaint("June", "Camaleão");
        june.setGenero(Genero.FEMININO);
        june.perderVida(15.5);
        lista.adicionar(june);
        
        GoldSaint dohko = new GoldSaint("Dohko", "Áries");
        dohko.perderVida(90);
        dohko.vestirArmadura();
        lista.adicionar(dohko);
        
        assertEquals("June,84.5,Camaleão,BRONZE,VIVO,FEMININO,false\nDohko,10.0,Áries,OURO,VIVO,NAO_INFORMADO,true", lista.getCSV());
    }
    
    @Test
    public void getCSVComNenhumSaint() {
        ListaSaints lista = new ListaSaints();
        assertEquals("", lista.getCSV());
    }
}