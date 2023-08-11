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
        return formattedPhoneNumber();
    }

    /** Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String formattedPhoneNumber () {
        String num = this.phoneNumber;
        if (num.length() == 10) {
            num = num.substring(0, 3) + "-" + num.substring(3, 6) + "-" + num.substring(6, num.length());
        } else {
            num = num.substring(0, 3) + "-" + num.substring(3, num.length());
        }
        return num;
    }
    public String toString() {
        return this.name + " | " + formattedPhoneNumber();
    }
}
