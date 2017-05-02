package company.model;

/**
 * Created by Alexander on 10.09.2016.
 */
public class Book {

    private int id;
    private String name;
    private String author;
    private String desc;
    private byte[] icon;

    public Book() {
    }

    public Book(int id, String name, String author, String desc, byte[] icon) {
        this.name = name;
        this.author = author;
        this.desc = desc;
        this.icon = icon;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name, String author, String desc) {
        this.name = name;
        this.author = author;
        this.desc = desc;
    }

    public Book(int id, String name, String author, String desc) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
