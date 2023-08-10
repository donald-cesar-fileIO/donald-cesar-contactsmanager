import java.util.ArrayList;
import util.Input;

public class ContactMenu {

    private static Contact enterContact() {
        Input input = new Input();
        String name = input.getString("Enter your name? ");
        System.out.println("You entered: " + name);
        String phoneNumber = input.getString("Enter your phone number: ");
        System.out.println("You entered: " + phoneNumber);
        return new Contact(name, phoneNumber);
    }


    public static void main(String[] args) {
        ArrayList<Contact> contactList = new ArrayList<>();

        Contact donald = new Contact("Donald Twitty", "00029394");

        contactList.add(donald);

        for (Contact contact: contactList) {
            System.out.println(contact.getName() + " " + contact.getNumber());
        }

        Contact newContact = enterContact();
        contactList.add(newContact);

        for (Contact contact: contactList) {
            System.out.println(contact.getName() + " " + contact.getNumber());
        }







    }
}
