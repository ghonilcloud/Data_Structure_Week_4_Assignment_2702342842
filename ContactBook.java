import java.util.LinkedList;
import java.util.Scanner;

public class ContactBook {
    private LinkedList<Contact> contacts;
    private Scanner scanner;

    public ContactBook() {
        contacts = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        contactBook.runContactBook();
    }

    private void runContactBook() {
        boolean quit = false;

        while (!quit) {
            System.out.println("******************************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail Search");
            System.out.println("(P)rint List");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("******************************");
            System.out.print("Please enter a command: ");
            String input = scanner.nextLine();

            switch (input.toUpperCase()) {
                case "A":
                    addContact();
                    break;
                case "D":
                    deleteContact();
                    break;
                case "E":
                    emailSearch();
                    break;
                case "P":
                    printList();
                    break;
                case "S":
                    search();
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        System.out.println("Quitting program.");
        scanner.close();
    }

    private void addContact() {
        System.out.print("Enter contact's name: ");
        String name = scanner.nextLine();
        String namelower = name.toLowerCase();

        System.out.print("Enter contact's phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter contact's email: ");
        String email = scanner.nextLine();
        String emaillower = email.toLowerCase();

        Contact contact = new Contact(namelower, phoneNumber, emaillower);
        contacts.add(contact);

        System.out.println("Contact added successfully.");
    }

    private void deleteContact() {
        System.out.print("Enter the email of the contact you want to delete: ");
        String email = scanner.nextLine();
        String emaillower = email.toLowerCase();

        int indexToRemove = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getEmail().equals(emaillower)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            contacts.remove(indexToRemove);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("No contact found with that email.");
        }
    }

    private void emailSearch() {
        System.out.print("Enter the email you want to search: ");
        String email = scanner.nextLine();
        String emaillower = email.toLowerCase();

        for (Contact contact : contacts) {
            if (contact.getEmail().equals(emaillower)) {
                System.out.println("Contact found:");
                System.out.println(contact.toString());
                return;
            }
        }

        System.out.println("No contact found with that email.");
    }

    private void printList() {
        System.out.println("Contact List:");
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    private void search() {
        System.out.print("Enter the name you want to search: ");
        String name = scanner.nextLine();
        String namelower = name.toLowerCase();

        for (Contact contact : contacts) {
            if (contact.getName().equals(namelower)) {
                System.out.println("Contact found:");
                System.out.println(contact.toString());
                return;
            }
        }

        System.out.println("No contact found with that name.");
    }

    private static class Contact {
        // Defining the three needed strings.
        private String name;
        private String phoneNumber;
        private String email;

        // Constructor, takes the three strings to create the Contact class.
        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        // Used for option (S)earch, in order to match the user's input with a matching name in the contact list.
        public String getName() {
            return name;
        }

        // Used for option (D)elete and (E)mail Search, in order to get a matching email within a contact list.
        public String getEmail() {
            return email;
        }

        // Used by (P)rint List, (E)mail Search, and (S)earch to print out the string format of the contacts.
        @Override
        public String toString() {
            return "Contact: " + "Name = '" + name + '\'' + ", Phone Number = '" + phoneNumber + '\'' + ", Email = '" + email + '\'';
        }
    }
}
