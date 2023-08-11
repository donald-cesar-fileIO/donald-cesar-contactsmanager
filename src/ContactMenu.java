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
        if (phoneNumber.length() != 10 && phoneNumber.length() != 7) {
            System.out.println("Phone number must contain at least 10 or 7 digits");
        }

        } while (phoneNumber.length() != 10 && phoneNumber.length() != 7) ;

        System.out.println("You entered: " + phoneNumber);
        contactList.add(new Contact(name, phoneNumber));
    }
    private static void findContact(ArrayList<Contact> contactList) {
        String userResponse = input.getString("Enter contact name");
//        Contact contactFound;
        boolean contactExist = false;
        for (Contact contact: contactList) {
            if(contact.getName().toLowerCase().contains(userResponse.toLowerCase())) {
                contactExist = true;
                System.out.println("Contact found: " + contact.getName() + " " + contact.getNumber());
            }
        }
        if(!contactExist) {
            System.out.println("No contact found");
        }
    }
    private static void deleteContact(ArrayList<Contact> contactList) {
        String userResponse = input.getString("Enter contact's name to be deleted: ");
        System.out.println(contactList.size());
        for(Contact contact : contactList) {
            if(contact.getName().toLowerCase().contains(userResponse.toLowerCase())) {
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
            Contact newGuy = new Contact(name, number.trim()); // We are instantiating a new contact and initializing it with the previously split string.
            newContactList.add(newGuy);
        }
        return newContactList;
    }

    private static void writeContactsToFile(ArrayList<Contact> contactList) {
        Path path = Paths.get("data/contacts.txt");
        ArrayList<String> strList = new ArrayList<>(); // Creating an List of strings... to be utilized to collect all formatted objects
        for (Contact contact: contactList) {
                    String txtString = contact.getName() + "," + contact.getNumber();
                    strList.add(txtString);
            }
        try{
            Files.write(path,strList);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void viewContacts (ArrayList<Contact> contactList) {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.format("%-20s | %-15s%n", "Name", "Phone Number");
            System.out.format("%-20s | %-15s%n", "-------------------", "---------------");
            for (Contact contact : contactList) {
                System.out.format("%-20s | %-15s%n", contact.getName(), contact.getNumber());
            }
        }
    }

    public static void main(String[] args) {

        /** General skeleton of `main` app:
         *
         * To Do:
         *
         * Options within each switch-case method?? Bring back to menu or remain in current switch case??
         *
         *
         * General:
         * Formatting, bonuses.
         *
         * */

        directoryAndFile();

        int menuResponse;
        do {
            menuResponse = contactMenu();
            switch (menuResponse) {
                case 1:
                    System.out.println("You picked 1");
                    viewContacts (contactList);
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

        writeContactsToFile(contactList);




    }
}
