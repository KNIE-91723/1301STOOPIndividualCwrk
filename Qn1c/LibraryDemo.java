package Qn1c;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// BOOK CLASS

class Book 
{
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    
    public Book(String isbn, String title) 
    {
        this(isbn, title, "Unknown");
    }

    
    public Book(String isbn, String title, String author) 
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true; 
    }

    public String getIsbn()
    { 
        return isbn; 
    }

    public void setIsbn(String isbn) 
    { 
        this.isbn = isbn; 
    }

    public String getTitle() 
    { 
        return title; 
    }

    public void setTitle(String title) 
    { 
        this.title = title; 
    }

    public String getAuthor() 
    { 
        return author; 
    }

    public void setAuthor(String author) 
    { 
        this.author = author; 
    }

    public boolean isAvailable() 
    { 
        return available; 
    }

    public void setAvailable(boolean available) 
    { 
        this.available = available; 
    }

    @Override
    public String toString() 
    {
        return String.format("Book [ISBN: %s | Title: '%s' | Author: %s | Status: %s]", 
            isbn, title, author, (available ? "Available" : "On Loan"));
    }
}

// MEMBER CLASS

class Member 
{
    private String memberId;
    private String name;
    private List<Loan> currentLoans;

    public Member(String memberId, String name) 
    {
        this.memberId = memberId;
        this.name = name;
        this.currentLoans = new ArrayList<>();
    }

    public String getMemberId() 
    { 
        return memberId; 
    }

    public void setMemberId(String memberId) 
    { 
        this.memberId = memberId; 
    }

    public String getName() 
    { 
        return name; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public List<Loan> getCurrentLoans() 
    { 
        return currentLoans; 
    }

    public void setCurrentLoans(List<Loan> currentLoans) 
    { 
        this.currentLoans = currentLoans; 
    }

    public void addLoan(Loan loan) 
    { 
        this.currentLoans.add(loan); 
    }

    public void removeLoan(Loan loan) 
    { 
        this.currentLoans.remove(loan); 
    }

    @Override
    public String toString() 
    {
        return String.format("Member [ID: %s | Name: %s | Current Loans Held: %d]", 
            memberId, name, currentLoans.size());
    }
}

// LOAN CLASS

class Loan
{
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Loan(Member member, Book book, int loanPeriodDays) 
    {
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = this.borrowDate.plusDays(loanPeriodDays);
    }

    public Member getMember() 
    { 
        return member; 
    }

    public void setMember(Member member) 
    { 
        this.member = member;
    }

    public Book getBook() 
    { 
        return book; 
    }

    public void setBook(Book book) 
    { 
        this.book = book; 
    }

    public LocalDate getBorrowDate() 
    { 
        return borrowDate; 
    }

    public void setBorrowDate(LocalDate borrowDate) 
    { 
        this.borrowDate = borrowDate; 
    }

    public LocalDate getDueDate() 
    { 
        return dueDate; 
    }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return String.format("Loan [Member: %s | Book: '%s' | Borrowed: %s | Due: %s]", 
            member.getName(), book.getTitle(), borrowDate, dueDate);
    }
}

// LIBRARY CLASS

class Library 
{
    private List<Book> books;
    private List<Member> members;

    public Library() 
    {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public List<Book> getBooks() 
    { 
        return books; 
    }

    public void setBooks(List<Book> books) 
    { 
        this.books = books; 
    }

    public List<Member> getMembers() 
    { 
        return members; 
    }

    public void setMembers(List<Member> members) 
    { 
        this.members = members; 
    }

    public void addBook(Book book) 
    { 
        this.books.add(book); 
    }

    public void registerMember(Member member) 
    { 
        this.members.add(member); 
    }

    public void lendBook(Member member, Book book) 
    {
        // Enforce the active loan restriction rule

        if (!book.isAvailable()) 
        {
            System.out.println("REJECTED GRACEFULLY: Transaction Denied! '"
                    + book.getTitle() + "' is currently on loan to another member.");
            return;
        }

        // Instantiate a 14-day loan transaction mapping
        Loan standardLoan = new Loan(member, book, 14);

        //Sync references across elements
        book.setAvailable(false); // Book status is now locked
        member.addLoan(standardLoan); // Loan record is appended to the member profile

        System.out
                .println("SUCCESS: '" + book.getTitle() + "' has been successfully lent to " + member.getName() + ".");
    }

    public void returnBook(Book book)
    {
        // Verify if the book is actually out on loan

        if (book.isAvailable()) 
        {
            System.out.println("ERROR: Processing exception. '" + book.getTitle()
                    + "' is already present in the library inventory.");
            return;
        }

        // Locate and sweep the transaction reference out of the active registry

        for (Member structuralMember : members) 
        {
            boolean removed = structuralMember.getCurrentLoans()
                    .removeIf(activeLoan -> activeLoan.getBook().getIsbn().equals(book.getIsbn()));

            if (removed)
            {
                break; // Break loop early once the active reference path is caught
            }
        }

        book.setAvailable(true);
        System.out.println(
                "SUCCESS: '" + book.getTitle() + "' was cleanly checked back into the system inventory register.");
    }

    public void searchBookByTitle(String title) 
    {
        System.out.println("\n--- Catalog Search Interface Engine ---");
        for (Book b : books) 
        {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) 
            {
                System.out.println("  " + b);
            }
        }
    }
    
    @Override
    public String toString() 
    {
        return String.format("Library Management State [Total Cataloged Volumes: %d | Registered Borrowers: %d]", 
            books.size(), members.size());
    }
}
public class LibraryDemo 
{
    public static void main(String[] args) 
    {
        Library library = new Library();

        Book book1 = new Book("101", "The Hobbit", "J.R.R. Tolkien"); 
        Book book2 = new Book("102", "1984", "George Orwell");        
        Book book3 = new Book("103", "Java Programming Basics");       

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        Member member1 = new Member("M-001", "Alice Smith");
        Member member2 = new Member("M-002", "Bob Jones");

        library.registerMember(member1);
        library.registerMember(member2);


        System.out.println("       LIBRARY STATUS REPORT: INITIAL STATE       ");
        System.out.println("=================================================");
        System.out.println(library);
        System.out.println("\nDetailed Inventory:");
        System.out.println("  -> " + book1);
        System.out.println("  -> " + book2);
        System.out.println("  -> " + book3);
        System.out.println("\nDetailed Members:");
        System.out.println("  -> " + member1);
        System.out.println("  -> " + member2);
        System.out.println("=================================================\n");

        System.out.println("============= TRANSACTION LOG SYSTEM ============");
        
        library.lendBook(member1, book1);
        
        // Attempting to give Alice's book directly to Bob before checking it in.
        library.lendBook(member2, book1); 
        
        // Transaction C: Independent alternative processing path
        library.lendBook(member2, book2);

        // Transaction D: Reversing state markers back into active registry
        library.returnBook(book1);
        System.out.println("_____________________________________________________\n");

        System.out.println("        LIBRARY STATUS REPORT: FINAL STATE        ");
        System.out.println("_________________________________________________");
        System.out.println(library);
        System.out.println("\nDetailed Inventory:");
        System.out.println("  -> " + book1);
        System.out.println("  -> " + book2);
        System.out.println("  -> " + book3);
        System.out.println("\nDetailed Members:");
        System.out.println("  -> " + member1);
        System.out.println("  -> " + member2);
    }
}

