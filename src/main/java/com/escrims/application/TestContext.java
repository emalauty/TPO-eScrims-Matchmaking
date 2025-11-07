package com.escrims.application;

/**
 * Patron Singleton.
 * Esta es una clase "hack" solo para la DEMO.
 * Actua como un "buzon" global donde la GUI puede
 * dejar el email de prueba para que el Subscriber lo lea.
 */
public class TestContext {

    private static TestContext instance;
    private String emailDePrueba = "pone-tu-mail-aqui@ejemplo.com"; // Mail por defecto

    private TestContext() {}

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    /**
     * Metodo para que la GUI "deje" el mail
     */
    public void setEmailDePrueba(String email) {
        System.out.println("TEST_CONTEXT: Email de prueba seteado a: " + email);
        this.emailDePrueba = email;
    }

    /**
     * Metodo para que el Subscriber "lea" el mail
     */
    public String getEmailDePrueba() {
        return emailDePrueba;
    }
}