package exceptions;

public class UnsupportedBrowserException extends RuntimeException{

    public UnsupportedBrowserException(String browser) {
        super(String.format("Браузер %s не поддерживается", browser));
    }
}
