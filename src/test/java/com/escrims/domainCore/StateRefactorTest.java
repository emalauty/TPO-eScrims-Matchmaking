package com.escrims.domainCore;

// Importamos las herramientas de JUnit
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Para 'assertEquals', 'assertThrows', etc.

/**
 * Test Unitario para el Patron State (Modo Complejo/Refactorizado).
 * Esto prueba que las transiciones y excepciones funcionen.
 */
public class StateRefactorTest {

    private Scrim scrim;
    private Usuario usuarioDePrueba;
    private Rol rolDePrueba;

    // @BeforeEach: Este metodo se ejecuta ANTES de cada @Test
    @BeforeEach
    public void setUp() {
        // Creamos un Scrim "limpio" para cada prueba
        scrim = new Scrim();
        usuarioDePrueba = new Usuario();
        rolDePrueba = new Rol("Tester");
    }

    @Test
    public void test_ScrimIniciaEnBuscandoState() {
        // Prueba 1: Asegurarnos de que el estado inicial sea el correcto
        assertNotNull(scrim.getEstadoActual());
        assertTrue(scrim.getEstadoActual() instanceof BuscandoState, "El Scrim deberia empezar en BuscandoState");
    }

    @Test
    public void test_BuscandoState_PermitePostular() {
        // Prueba 2: Probar que 'BuscandoState' (que es IPostulable)
        // realmente deja postularse.

        // 'assertDoesNotThrow' verifica que el codigo de adentro NO lance una excepcion
        assertDoesNotThrow(() -> {
            scrim.postular(usuarioDePrueba, rolDePrueba);
        }, "BuscandoState deberia permitir postularse");

        // Verificamos el resultado
        assertEquals(1, scrim.getPostulaciones().size(), "La postulacion deberia haberse agregado");
    }

    @Test
    public void test_BuscandoState_LanzaErrorAlConfirmar() {
        // Prueba 3: Probar que 'BuscandoState' (que NO es IConfirmable)
        // lanza el error que esperamos.

        // Verificamos que al llamar a 'scrim.confirmar()', se lance SI O SI
        // una 'UnsupportedOperationException'.
        assertThrows(UnsupportedOperationException.class, () -> {
            scrim.confirmar(usuarioDePrueba);
        }, "BuscandoState no deberia permitir confirmar");
    }

    @Test
    public void test_BuscandoState_LanzaErrorAlIniciar() {
        // Prueba 4: Probar que 'BuscandoState' (que NO es IIniciable)
        // lanza el error que esperamos.

        assertThrows(UnsupportedOperationException.class, () -> {
            scrim.iniciar();
        }, "BuscandoState no deberia permitir iniciar");
    }

}