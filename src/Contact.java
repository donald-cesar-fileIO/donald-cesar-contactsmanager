public class Contact {

    /** Instance variables */
    protected String name;
    protected String phoneNumber;

    /** Constructor */
    public Contact (String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /** Getters */
    public String getName() {
        return this.name;
    }
    public String getNumber() {
        return this.phoneNumber;
    }

    /** Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** String format, not used */
//    public String toString() {
//        return this.name + " | " + this.phoneNumber;
//    }
}