import java.nio.file.StandardOpenOption;
import java.util.*;

import util.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactMenu {

    private static Input input = new Input();
    private static ArrayList<Contact> contactList = getContactsFromFile();

    private static void directoryAndFile() {
        String directory = "data";
        String fileName = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, fileName);

        System.out.println(dataFile);

        if(!Files.exists(dataDirectory)){
            try {
                Files.createDirectories(dataDirectory);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        if(!Files.exists(dataFile)){
            try {
                Files.createFile(dataFile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static String formattedPhoneNumber (String numStr) {
        if (numStr.length() == 10) {
            numStr= numStr.substring(0, 3) + "-" + numStr.substring(3, 6) + "-" + numStr.substring(6, numStr.length());
        } else {
            numStr = numStr.substring(0, 3) + "-" + numStr.substring(3, numStr.length());
        }
        return numStr;
    }
    private static int contactMenu() {
        System.out.println("\n1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        int menuOption = input.getInt("Please pick an option: \n");
        return menuOption;
    }
    private static void viewContacts (ArrayList<Contact> contactList) {
        do {
            System.out.println("\nView Contacts: ");
            if (contactList.isEmpty()) {
                System.out.println("No contacts found.");
            } else {
                System.out.format("%-20s | %-15s%n", "Name", "Phone Number");
                System.out.format("%-20s | %-15s%n", "-------------------", "---------------");
                for (Contact contact : contactList) {
                    System.out.format("%-20s | %-15s%n", contact.getName(), formattedPhoneNumber(contact.getNumber()));
                }
            }
        } while (!input.yesNo("\nback to main menu, enter [y]"));
    }
    private static void addContact(ArrayList<Contact> contactList) {
        do {
            System.out.println("Add a new contact... ");
            String name = input.getString("Enter contact's name: ");
            System.out.println("You entered: " + name);
            String phoneNumber;

            do {
                phoneNumber = input.getString("Enter contact's phone number: ");
                for (int i = 0; i < phoneNumber.length(); i++) {
                    if (!Character.isDigit(phoneNumber.charAt(i))) {
                        System.out.println("Not a valid number");
                        break;
                    }
                }
                if (phoneNumber.length() != 10 && phoneNumber.length() != 7) {
                    System.out.println("Phone number must contain at least 7 or 10 digits");
                }

            } while (phoneNumber.length() != 10 && phoneNumber.length() != 7);

            System.out.println("You entered: " + formattedPhoneNumber(phoneNumber));
            System.out.println("Contact added: \n" + name + " | " + formattedPhoneNumber(phoneNumber));
            contactList.add(new Contact(name, phoneNumber));
        } while(input.yesNo("Do you want to add another contact? [y/N]"));
    }
    private static void searchContact(ArrayList<Contact> contactList) {
        System.out.println("Search for a contact... ");
        do {
            String userResponse = input.getString("Enter contact's name: ");
            boolean contactExist = false;
            for (Contact contact : contactList) {
                if (contact.getName().toLowerCase().contains(userResponse.toLowerCase())) {
                    contactExist = true;
                    System.out.println("Contact found: \n" + contact.getName() + " | " + formattedPhoneNumber(contact.getNumber()) + "\n");
                }
            }
            if (!contactExist) {
                System.out.println("No contact found");
            }
        } while(input.yesNo("Do you want to look for another contact? [y/N]"));
    }
    private static void deleteContact(ArrayList<Contact> contactList) {
        System.out.println("Delete an existing contact...");
        do {
            String userResponse = input.getString("Enter contact's name to be deleted: ");
            boolean contactExists = false;
            Contact deletedContact = null;
            for (Contact contact : contactList) {
                if (contact.getName().toLowerCase().contains(userResponse.toLowerCase())) {
                    System.out.println("Contact found: " + contact.getName() + " | " + formattedPhoneNumber(contact.getNumber()));
                    contactExists = true;
                    deletedContact = contact;
                }
            }
            if(!contactExists) {
                System.out.println("No contact found");
            } else {
                if(input.yesNo("Are you sure you want to delete the contact [y/N]")) {
                    contactList.remove(deletedContact);
                    System.out.println("Contact deleted...");
                }
            }

        } while (input.yesNo("Do you want to delete another contact? [y/N]"));
    }
    private static ArrayList<Contact> getContactsFromFile(){
        ArrayList<Contact> newContactList = new ArrayList<>(); // Creating a temporary arrayList of contacts (an array list of Object)
        Path path = Paths.get("data/contacts.txt"); // Path object, path variable, and path method that points to the file.
        ArrayList<String> stringList = new ArrayList<>(); // Creating a temporary arrayList of strings... to be able to communicate to data/contacts.txt file
        try {
            stringList = (ArrayList<String>) Files.readAllLines(path); // populating the list with the lines from the file.
        } catch (IOException e){
            e.printStackTrace();
        }
        for (String contact : stringList) { // iterating through the ArrayList<String>
            String[] arr = contact.split(","); // per element (name & phone), we are splitting with the delimeter of choice.
            String name = arr[0];
            String number = arr[1];
            Contact newGuy = new Contact(name.trim(), number.trim()); // We are instantiating a new contact and initializing it with the previously split string.
            newContactList.add(newGuy);
        }
        return newContactList;
    }
    private static void writeContactsToFile(ArrayList<Contact> contactList) {
        boolean saveChanges = input.yesNo("Do you want to save your changes? [y/N]");
        if (saveChanges) {
            Path path = Paths.get("data/contacts.txt");
            ArrayList<String> strList = new ArrayList<>(); // Creating an List of strings... to be utilized to collect all formatted objects
            for (Contact contact : contactList) {
                String txtString = contact.getName().trim() + "," + contact.getNumber().trim(); // No need for formattedPhoneNumber() method
                strList.add(txtString);
            }
            try {
                Files.write(path, strList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Changes saved, have a nice day!");
        } else {
            System.out.println("Have a nice day!");
        }
    }
    private static void contactsApp() {
        directoryAndFile();
        int menuResponse;
        do {
            menuResponse = contactMenu();
            switch (menuResponse) {
                case 1:
                    viewContacts(contactList);
                    break;
                case 2:
                    addContact(contactList);
                    break;
                case 3:
                    searchContact(contactList);
                    break;
                case 4:
                    deleteContact(contactList);
                    break;
            }
        } while (menuResponse != 5);
        writeContactsToFile(contactList);
    }

    public static void main(String[] args) {
        contactsApp();
    }
}