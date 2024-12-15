public class ObjReaderException extends RuntimeException {
    private final int lineInd;
    public ObjReaderException(String message, int lineInd) {
        super(message + " at line " + lineInd);
        this.lineInd = lineInd;
    }
    public int getLineInd() {
        return lineInd;
    }
}
