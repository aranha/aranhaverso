package br.com.gastronomia.exception;

public class UsuarioInativoException extends Exception {
    
    public UsuarioInativoException(String msg) {
        super(msg);
    }

    public UsuarioInativoException(String msg, Exception e) {
        super(msg, e);
    }

}
