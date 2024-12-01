public class ObjReaderException extends RuntimeException {
    private final int lineInd;

    // Конструктор с двумя параметрами: сообщением и номером строки
    public ObjReaderException(String message, int lineInd) {
        super(message + " at line " + lineInd); // Сообщение об ошибке с номером строки
        this.lineInd = lineInd;
    }

    // Геттер для номера строки
    public int getLineInd() {
        return lineInd;
    }
}
