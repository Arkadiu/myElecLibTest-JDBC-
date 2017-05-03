package company;

import company.dao.DBWorker;

public class MyLibApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MyLibApplication.class, args);
        /*
        DBWorker dbWorker = new DBWorker();

        dbWorker.getAllBooks();
        dbWorker.addBook(50, "Карлсон", "Линдра", "Карлсон который живет на крыше", "C:\\icon.png");
        dbWorker.addBook(NULL, "Мальчик с пальчик", "Перро", "Немецкая сказка", "C:\\icon.png");
        dbWorker.deleteBook(50);
        dbWorker.updateBook(10, "Сказка про мальчика");
        dbWorker.searchBookByNameOrAuthor("Гримм");
        dbWorker.addBook(NULL, "Русалочка", "Андерсон", "Сказка про подставу", "");
        */
        System.out.println(new DBWorker().getAllBooks());

    }
}
