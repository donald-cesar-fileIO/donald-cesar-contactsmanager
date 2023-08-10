public class Contact {

    /** Instance variables */
    protected String name;
    protected int number;

    /** Constructor */
    public Contact (String name, int number) {
        this.name = name;
        this.number = number;
    }

    /** Getters */
    public String getName() {
        return this.name;
    }
    public int getNumber() {
        return this.number;
    }

    /** Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
