import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// BOOK CLASS

public class Book 
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

    // to be done in Answer to part C
    public void lendBook(Member member, Book book) 
    {  

    }
    public void returnBook(Book book) 
    {  

    }

    public Book searchBookByTitle(String title) 
    { 
        return null; 
    }
    
    @Override
    public String toString() {
        return String.format("Library Management State [Total Cataloged Volumes: %d | Registered Borrowers: %d]", 
            books.size(), members.size());
    }
}