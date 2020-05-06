package model;

import enums.Libraries;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private String bookName;
    private int pages;
    private int publishedYear;
    private String writer;
    private String language;
    private long ISBN;
    private String translatorName;
    private int numbersAvailable;
    private String companyName;
    private double price;
    private Libraries bookPlace;
    private Map<Person, String> borrowers;

    public Book(String bookName, int pages, int publishedYear,
                String writer, String language, long ISBN, double price) {
        this.bookName = bookName;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.writer = writer;
        this.language = language;
        this.ISBN = ISBN;
        this.price = price;
        this.translatorName = "";
        this.numbersAvailable = 0;
        this.bookPlace = Libraries.NO_WHERE_YET;
        borrowers = new HashMap<>();
    }

    public Book(String bookName, int pages, int publishedYear, String writer,
                String language, long ISBN, double price, String translatorName) {
        this.bookName = bookName;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.writer = writer;
        this.language = language;
        this.ISBN = ISBN;
        this.price = price;
        this.translatorName = translatorName;
        this.numbersAvailable = 0;
        this.bookPlace = Libraries.NO_WHERE_YET;
        borrowers = new HashMap<>();
    }

    public Book(String bookName, long ISBN, int publishedYear) {
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
    }

    public Book(long ISBN, int publishedYear) {
        this.ISBN = ISBN;
        this.publishedYear = publishedYear;
    }

    public Book(String Name, int publishedYear, String translatorName) {//just for help!!
        this.bookName = Name;
        this.writer = Name;
        this.publishedYear = publishedYear;
        this.translatorName = translatorName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public String getWriter() {
        return writer;
    }

    public String getTranslatorName() {
        return translatorName;
    }

    public int getNumbersAvailable() {
        return numbersAvailable;
    }

    public Libraries getBookPlace() {
        return bookPlace;
    }

    public void setNumbersAvailable(int numbersAvailable) {
        this.numbersAvailable = numbersAvailable;
    }

    public void setBookPlace(Libraries bookPlace) {
        this.bookPlace = bookPlace;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", pages=" + pages +
                ", publishedYear=" + publishedYear +
                ", writer='" + writer + '\'' +
                ", language='" + language + '\'' +
                ", ISBN=" + ISBN +
                ", translatorName='" + translatorName + '\'' +
                ", numbersAvailable=" + numbersAvailable +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", bookPlace=" + bookPlace +
                ", borrowers=" + borrowers +
                '}';
    }
}
