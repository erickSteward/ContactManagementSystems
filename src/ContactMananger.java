import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class ContactMananger {
  private Map<String, Contact> contacts;
  
  private Map<String, Set<String>> groups;

  private Scanner scanner;

  //constructor
  public ContactMananger(){
    contacts = new HashMap<>();
    groups = new HashMap<>();
    scanner = new Scanner(System.in);
  }

    public void run(){
      while(true){
        System.out.println("\n-----------Contact Management System --------");
        System.out.println("1. Add a contact");
        System.out.println("2. View all Contact");
        System.out.println("3. Edit a contact");
        System.out.println("4. Delete contacts");
        System.out.println("5. Search contacts");
        System.out.println("6. Add contacts to groups");
        System.out.println("7. View contacts to group");
        System.out.println("8. Exit");
        System.out.print("9. Enter Your Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            addContactMenu();
            break;
          case 2:
            viewAllContacts();
            break;
          case 3:
            editContactMenu();
            break;
          case 4:
            deleteContactMenu();
            break;
          case 5:
            searchContactMenu();
            break;
          case 6:
            addContactToGroupMenu();
            break;
          case 7:
            viewContactsToGroupsMenu();
            break;
          case 8:
            System.out.println("Thank you for using Contact Managment System");
            return;
          default:
            System.out.println("Invalid choice. Please Try again.");
        }
      }
    }

    //A method to add a new contact
    public void addContactMenu(){
      System.out.print("Enter Contact Name: ");
      String name = scanner.nextLine();
      System.out.print("Enter Contact Phone Number: ");
      String phoneNumber = scanner.nextLine();
      System.out.print("Enter Contact Email Address: ");
      String email = scanner.nextLine();

      Contact contact = new Contact(name, phoneNumber, email);

      contacts.put(name, contact);
      System.out.println("Contact Added Succefully.");
    }

    //A method to view/display all Contacts
    public void viewAllContacts(){
      if (contacts.isEmpty()) {
        System.out.println("No Contacts Found");
      }

      for(Contact contact : contacts.values()){
          System.out.println(contact);
      }
    }

    //A method to Edit a contact
    public void editContactMenu(){
      System.out.print("Enter the name of the contact to Edit: ");
      String name = scanner.nextLine();
      
      Contact contact = contacts.get(name);

      if (contact == null) {
        System.out.println("Contact not Found");
      } 

      System.out.println("Current Details: " + contact);

      System.out.print("Enter the new phone (Press Enter to keep the current): ");

      String phoneNumber = scanner.nextLine();

      if (!phoneNumber.isEmpty()) {
        contact.setPhoneNumber(phoneNumber);
      }

      System.out.print("Enter the new email (Press Enter to keep the current): ");
      String email = scanner.nextLine();

      if (!email.isEmpty()) {
        contact.setEmail(email);
      }

      System.out.println("Contact Updated Successfully");
      
    }

    //A method to delete a contact
    public void deleteContactMenu(){

      System.out.println("Enter the name of the contact to delete");
      String name = scanner.nextLine();

      Contact removedContact = contacts.remove(name);

      if ( removedContact != null) {
        System.out.println("Contact delete successfully: " + removedContact);
        //remove the contact from all groups.

        for(Set<String> groupMembers : groups.values()){
          groupMembers.remove(name);
        }

      } else {
        System.out.println("Contact not Found.");
      }

    }

    //A method to search a contact
    public void searchContactMenu(){
      System.out.println("Enter search term: ");
      String searchTerm = scanner.nextLine();
      boolean Found = false;

      for(Contact contact : contacts.values()){
        if (contact.getName().toLowerCase().contains(searchTerm) || contact.getEmail().toLowerCase().contains(searchTerm) || contact.getPhoneNumber().contains(searchTerm)) {
          System.out.println(contact.toString());
          Found = true;
        }
      }

      if (!Found) {
        System.out.println("Contact not found matching with the search term.");
      }
    }

    //A method to add contact to a group 
    public void addContactToGroupMenu(){
      System.out.println("Enter Contact name: ");
      String contactName = scanner.nextLine();

      if(!contacts.containsKey(contactName)){
        System.out.println("Contact not found.");
        return;
      }

      System.out.println("Enter group name: ");
      String groupName = scanner.nextLine();

      groups.computeIfAbsent(groupName, k -> new HashSet<>()).add(contactName);
      System.out.println("Contact Added to group successfully.");
      }

      //A method to view contacts in groups
      public void viewContactsToGroupsMenu(){
      System.out.print("Enter Group name: "); 
        String groupName = scanner.nextLine();

        Set<String> groupMembers  = groups.get(groupName);

        if (groupMembers == null || groupMembers.isEmpty()) {
          System.out.println("No Contacts found in this group");
        }

        System.out.println("Contacts in the recent Group " + groupName + ": " );

        for(String memberName : groupMembers){
          Contact contact = contacts.get(groupMembers);
          if(contact != null){
            System.out.println(contact);
          }
        }
      }

  }

