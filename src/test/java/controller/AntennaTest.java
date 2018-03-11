package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.Assert.*;

public class AntennaTest {

    private Antenna antenna;
    private OutputStream inputStream;

    @Before
    public void setUp() throws Exception {
        inputStream = new PipedOutputStream();
        antenna = new Antenna(inputStream, "wlp1s0");
        /*Como puedo mockear una interfaz wireless?? Ya depende del lado del lado del SO o
        se puedo buscar como mockear la clase NetworkINterface o PcpNetworkInterface.
        En este segundo caso, y creo que es el mejor, se debe cambiar el parámetro String que recibe la clase Antenna*/
    }

    @Test
    public void startListening() {
        antenna.startListening();

    }

    @Test
    public void stopListening() {
    }
}