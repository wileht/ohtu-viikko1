package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusEiVoiOllaNegatiivinen() {
    	Varasto huonoVarasto = new Varasto(-10);
    	assertEquals(0.0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitusTesti() {
    	Varasto tuplaVarasto = new Varasto(10, 1);
    	assertEquals(10, tuplaVarasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(1, tuplaVarasto.getSaldo(), vertailuTarkkuus);
    	
    	Varasto huonoinVarasto = new Varasto(-10, -2);
    	assertEquals(0, huonoinVarasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(0, huonoinVarasto.getSaldo(), vertailuTarkkuus);
    	
    	Varasto ihmeVarasto = new Varasto(10, 20);
    	assertEquals(10, ihmeVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoidaLisataLiikaa() {
    	varasto.lisaaVarastoon(220);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivista() {
    	varasto.lisaaVarastoon(-3);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivista() {
    	varasto.otaVarastosta(-3);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoidaOttaaLiikaa() {
    	varasto.lisaaVarastoon(10);
    	double montaOtettiin = varasto.otaVarastosta(220);
    	assertEquals(10, montaOtettiin, vertailuTarkkuus);
    }
    
    @Test
    public void toStringTest() {
    	String alkuTila = varasto.toString();
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", alkuTila);
    }
}