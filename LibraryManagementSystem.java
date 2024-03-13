import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int num;
    static Scanner sc = new Scanner(System.in);
    static String booksFilePath = "books.txt";
    static String usersFilePath = "users.txt";

    public static void main(String[] args) {
        int choice;
        Library L1 = new Library();  //Creating a library instance
        String ans;
        //Hard coding some values
        Book b1 = new Book("001", "Percy Jackson", "Rick Riordan", "Fantasy", "Available");
        L1.books.add(b1);
        saveBooksToFile(L1.books);
        User u1 = new User("000", "Zahra", "44-5904-098", new ArrayList<>());
        L1.users.add(u1);
        saveUsersToFile(L1.users);
        do {
            //Menu options display
            System.out.println("----------------Menu---------------- ");
            System.out.println("1) Add Books");
            System.out.println("2) Add Users");
            System.out.println("3) Display Books");
            System.out.println("4) Borrow or check out books");
            System.out.println("5) Return Books");
            System.out.println("6) Search for books by Author or name");
            System.out.println("7) Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); //User's input
            sc.nextLine(); // Consume newline
            //Choosing option based on the entered user input
            switch (choice) {
                case 1:
                //Add books
                int num = 0;
                boolean validInput = false;
                //Checking for numeric input
                do {
                    System.out.println("Enter the number of books you want to add:");
                    String input = sc.nextLine();
                    try {
                        num = Integer.parseInt(input);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                } while (!validInput);
                
                String[] add = new String[num];
                for (int i = 0; i < num; i++) {
                    System.out.println("Enter Book ID for Book no: " + (i + 1) + " :");
                    add[i] = sc.nextLine();
                }
                L1.addNewBook(add);  // Calling method to add books
                saveBooksToFile(L1.books);  // Saving books in array list to file
                break;
            
                case 2:
                //Add users
                int num1 = 0;
                boolean validInput1 = false;
                while (!validInput1) {
                    System.out.print("Enter number of users you want to add: ");
                    try {
                        num1 = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        validInput1 = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.nextLine(); // Clear the input buffer
                     }
                    }
                    String[] addUsers = new String[num1];
                    for (int i = 0; i < num1; i++) {
                        System.out.print("Enter user ID for User no: " + (i + 1) + " :");
                        addUsers[i] = sc.nextLine();
                    }
                L1.addNewUsers(addUsers); // Calling method to add users
                saveUsersToFile(L1.users); // Saving users in array list to file
                break;

                case 3:
                //Displaying Book info
                    System.out.println("Please write the title of the book you're looking for: ");
                    String title = sc.nextLine();
                    L1.displayBookFromFile(title); //Calling method to display info
                    break;

                case 4:
                //Borrow book
                    System.out.println("Enter user ID: ");
                    String userID = sc.nextLine();
                    System.out.println("Enter book ID: ");
                    String bookID = sc.nextLine();
                    
                    L1.borrowBook(bookID, userID); //Calling method to borrow books
                    saveBooksToFile(L1.books); //Saving updated book info
                    saveUsersToFile(L1.users); //Saving updated user info
                    break;

                case 5:
                //Return books
                    System.out.println("Enter user ID: ");
                    userID = sc.nextLine();
                    System.out.println("Enter book ID: ");
                    bookID = sc.nextLine();
                    L1.returnBook(userID, bookID); // Calling method to return book
                    saveBooksToFile(L1.books); // Saving updated book info
                    saveUsersToFile(L1.users); // Saving updated user info
                    break;

                case 6:
                //Search for books by author or name
                System.out.print("Enter name of Author or book: ");
                String name = sc.nextLine();
                L1.searchingForBooksFromFile(name); //Calling method to search for books
                break;
                default:
                System.out.println("Please enter options within the given range");
            }
            if(choice >= 1 && choice <=6){
            System.out.println("Do you wish to open the menu and continue updating information?(yes/no)");
            ans = sc.nextLine().toLowerCase();

            if (ans.equals("yes")) {
                
            }
            if (ans.equals("no")) {
                System.out.println("-------------------Closing application..");
                System.exit(0);  //Exit the application
            }
        }

        } while (choice != 7);
        sc.close();
        System.exit(0); //Exit application
    }

    //Book Class
    public static class Book {
        String bookID;
        String title;
        String author;
        String genre;
        String availabilityStatus;
        //Parametrized Constructor
        public Book(String bookID, String title, String author, String genre, String availabilityStatus) {
            this.bookID = bookID;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.availabilityStatus = availabilityStatus;
        }
        //Getters
        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }

    //User class
    static class User {
        String userID;
        String name;
        String contactInformation;
        ArrayList<String> borrowedBooks; 
        //Parametrized Constructor
        public User(String userID, String name, String contactInformation, ArrayList<String> borrowedBooks) {
            this.userID = userID;
            this.name = name;
            this.contactInformation = contactInformation;
            this.borrowedBooks = new ArrayList<>(borrowedBooks); // Initialize ArrayList
        }
    }
    //Library Class
    static class Library {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        //Adding book method
        void addNewBook(String[] bookIDs) {
            for (String bookID : bookIDs) {
                System.out.println("Enter title of the book with ID " + bookID + ": ");
                String title = sc.nextLine();
                System.out.println("Enter author of the book: ");
                String author = sc.nextLine();
                System.out.println("Enter genre of the book: ");
                String genre = sc.nextLine();
                System.out.println("Enter availability status(available/unavailable): ");
                String avail = sc.nextLine();
                Book addBook = new Book(bookID, title, author, genre, avail);
                books.add(addBook);
                System.out.println("----Book added!----");
            }
        }
        //Adding users method
        void addNewUsers(String[] userIDs) {
            for (String userID : userIDs) {
                System.out.println("Enter name for user " + userID + ": ");
                String name = sc.nextLine();
                System.out.println("Enter contact information for user " + userID + ": ");
                String contactInformation = sc.nextLine();
                System.out.println("Enter NUMBER of borrowed books for user " + userID + ": ");
                int n = sc.nextInt();
                sc.nextLine(); // Consume newline
                ArrayList<String> borrowedBooks = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Enter borrowed book " + (i + 1) + " for user: ");
                    borrowedBooks.add(sc.nextLine());
                }
                User newUser = new User(userID, name, contactInformation, borrowedBooks);
                users.add(newUser);
                System.out.println("----User added!----");
            }
        }
        
        //Method to display book details from file
        void displayBookFromFile(String title) {
            try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[1].equalsIgnoreCase(title)) {
                        System.out.println("Book name: " + parts[1]);
                        System.out.println("Book ID is: " + parts[0]);
                        System.out.println("Author name: " + parts[2]);
                        System.out.println("Genre is: " + parts[3]);
                        System.out.println("Availability status: " + parts[4]);
                        return;
                    }
                }
                System.out.println("The book: " + title + " has not been found in our system.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        //Method to borrow books
        void borrowBook(String bookID, String userID) {
            Book borrowedBook = findBookbyID(bookID);
            //Check if book exists
            if (borrowedBook != null) {
                //Update book's availability status
                if (borrowedBook.availabilityStatus.equalsIgnoreCase("Available")) {
                    borrowedBook.availabilityStatus = "Borrowed";
                    //Check if user exists
                    User user = findUserbyID(userID);
                    if (user != null) {
                        //Update user info
                        user.borrowedBooks.add(bookID);
                        System.out.println("Book with ID " + bookID + " has been successfully borrowed");
                        //Saving updated info
                        saveBooksToFile(books);
                        //Saving updated info
                        saveUsersToFile(users);
                    } else {
                        System.out.println("User with ID " + userID + " was not found in the library");
                    }
                } else {
                    System.out.println("Sorry, the book with ID " + bookID + " is not currently available for borrowing/issuing");
                }
            } else {
                System.out.println("Book with ID " + bookID + " not found in the library");
            }
        }
        //Method to return books
        void returnBook(String userID, String bookID) {
            // Check if the user exists in the library
            User user = findUserbyID(userID);
            if (user != null) {
                // Check if the user has borrowed the book
                if (user.borrowedBooks.contains(bookID)) {
                    // Update the book's availability status
                    Book returnedBook = findBookbyID(bookID);
                    if (returnedBook != null) {
                        returnedBook.availabilityStatus = "Available";
                        // Remove the book from the user's borrowed books list
                        user.borrowedBooks.remove(bookID);
                        System.out.println("Book with ID " + bookID + " has been successfully returned.");
                        //Saving updated info
                        saveBooksToFile(books);
                        //Saving updated info
                        saveUsersToFile(users);
                        return; 
                    } else {
                        System.out.println("Book with ID " + bookID + " not found in the library.");
                    }
                } else {
                    System.out.println("User with ID " + userID + " has not borrowed the book with ID " + bookID + ".");
                }
            } else {
                System.out.println("User with ID " + userID + " not found in the library.");
            }
        }
        //Method for finding book by its ID number
        Book findBookbyID(String bookID) {
            try(BufferedReader rd = new BufferedReader(new FileReader("books.txt"))){
                String l;
                while((l = rd.readLine()) != null){
                    String[] pts = l.split(",");
                    if(pts[0].equals(bookID)){
                        return new Book(pts[0], pts[1], pts[2], pts[3],pts[4]);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        //Method for finding user by userID
        User findUserbyID(String userID) {
            try (BufferedReader rd = new BufferedReader(new FileReader("users.txt"))) {
                String ln;
                while ((ln = rd.readLine()) != null) {
                    String[] pts = ln.split(",");
                    if (pts[0].equals(userID)) {
                        ArrayList<String> borrowedBks = new ArrayList<>();
                        if (pts.length > 3) {
                            String[] bookIDs = pts[3].split("Borrowed Books: ");
                            borrowedBks.addAll(Arrays.asList(bookIDs));
                        }
                        return new User(pts[0], pts[1], pts[2], borrowedBks);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //Method for searching books in file
        void searchingForBooksFromFile(String name) {
            try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
                boolean found = false;
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[1].equalsIgnoreCase(name) || parts[2].equalsIgnoreCase(name)) {
                        System.out.println("Book found:");
                        System.out.println("Book ID: " + parts[0]);
                        System.out.println("Title: " + parts[1]);
                        System.out.println("Author: " + parts[2]);
                        System.out.println("Genre: " + parts[3]);
                        System.out.println("Availability: " + parts[4]);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Book not found.");
                }
                //Approach for error handling
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Method for saving books to file
    static void saveBooksToFile(ArrayList<Book> books) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(booksFilePath));
            for (Book book : books) {
                writer.write(book.bookID + ","  + book.title + "," + book.author + "," + book.genre + "," + book.availabilityStatus);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Method for saving users to file
    static void saveUsersToFile(ArrayList<User> users) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath));
            for (User user : users) {
                // Join borrowed books with commas
                String borrowedBooks = String.join(",", user.borrowedBooks);
                // Write user details to file
                writer.write(user.userID + "," + user.name + "," + user.contactInformation + "," + borrowedBooks);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
