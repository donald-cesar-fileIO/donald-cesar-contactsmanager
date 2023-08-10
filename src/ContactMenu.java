import java.util.ArrayList;
import java.util.Objects;

import util.Input;

public class ContactMenu {

    private static Input input = new Input();
    private static ArrayList<Contact> contactList = new ArrayList<>();

    private static void enterContact(ArrayList<Contact> contactList) {
        String name = input.getString("Enter your name? ");
        System.out.println("You entered: " + name);
        String phoneNumber;

        do {
        phoneNumber = input.getString("Enter your phone number: ");
        for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    System.out.println("Not a valid number");
                    break;
                }
            }
        if (phoneNumber.length() != 10) {
            System.out.println("Phone number must contain at least 10 digits");
        }

        } while (phoneNumber.length() != 10);

        System.out.println("You entered: " + phoneNumber);
        contactList.add(new Contact(name, phoneNumber));
    }
    private static void findContact(ArrayList<Contact> contactList) {
        String userResponse = input.getString("Enter contact name");
        for (Contact contact: contactList) {
            if(contact.getName().contains(userResponse)) {
                System.out.println("Contact found: " + contact.getName() + " " + contact.getNumber());
            } else {
                System.out.println("No contact found");
            }
        }
    }
    private static void deleteContact(ArrayList<Contact> contactList) {
        String userResponse = input.getString("Enter contact's name to be deleted: ");
        System.out.println(contactList.size());
        Contact contactToDelete;
        for(Contact contact : contactList) {
            if(contact.getName().contains(userResponse)) {
                System.out.println("Contact found: " + contact.getName() + " " + contact.getNumber());
                contactList.remove(contact);
                break;
            }
        }
    }

    private static int contactMenu() {
        System.out.println("\n1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete and existing contact");
        System.out.println("5. Exit");
        int menuOption = input.getInt("Please pick an option: ");
        return menuOption;
    }



    public static void main(String[] args) {


//        for (Contact contact: contactList) {
//            System.out.println(contact.getName() + " " + contact.getNumber());
//        }

//        Contact newContact = enterContact();
//        contactList.add(newContact);

//        for (Contact contact: contactList) {
//            System.out.println(contact.getName() + " " + contact.getNumber());
//        }

//        System.out.println("ArrayList: " + contactList);
//        System.out.println(contactList.size());

//        for (Contact contact: contactList) {
//            if(contact.getName().contains("Don")) {
//                System.out.println("Contact found: " + contact.getName() + " " + contact.getNumber());
//            } else {
//                System.out.println("No contact found");
//            }
//        }
        int menuResponse;
        do {
            menuResponse = contactMenu();
            switch (menuResponse) {
                case 1:
                    System.out.println("You picked 1");
                    // For now, contacts displayed are just in the ArrayList, not the file
                    System.out.println(contactList);
                    break;
                case 2:
                    System.out.println("You picked 2");
                    enterContact(contactList);

                    break;
                case 3:
                    System.out.println("you picked 3");
                    findContact(contactList);
                    break;
                case 4:
                    System.out.println("You picked 4");
                    deleteContact(contactList);
                    break;
            }

        } while (menuResponse != 5);




    }
}
