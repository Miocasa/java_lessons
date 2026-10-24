package ProgrammingBook;

public class CodeListing {
    private String code;

    public CodeListing(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("\n[CODE START]\n%s\n[CODE END]\n", code);
    }
}