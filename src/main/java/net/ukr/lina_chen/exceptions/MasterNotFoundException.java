package net.ukr.lina_chen.exceptions;

public class MasterNotFoundException extends Exception {
    public MasterNotFoundException(Long masterId) {
        super("Master with id " + masterId + " not found");
    }

    public MasterNotFoundException(String message) {
        super(message);
    }
}
