package company;

import company.dao.DBWorker;

import static java.sql.Types.NULL;


public class MyLibApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MyLibApplication.class, args);
        DBWorker dbWorker = new DBWorker();

        dbWorker.showAllRow();
        dbWorker.addBook(5, "Карлсон", "Линдра", "Карлсон который живет на крыше", "C:\\icon.png");
        dbWorker.addBook(NULL, "Мальчик с пальчик", "Перро", "Немецкая сказка", "C:\\icon.png");
        dbWorker.deleteBook(5);
        dbWorker.updateBookDescription(10, "Сказка про мальчика");
        dbWorker.searchBookByNameOrAuthor("Гримм");
        dbWorker.addBook(NULL, "Русалочка", "Андерсон", "Сказка про подставу", "");

    }
}
