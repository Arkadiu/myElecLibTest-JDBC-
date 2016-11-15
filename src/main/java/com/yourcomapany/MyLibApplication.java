package com.yourcomapany;

import static java.sql.Types.NULL;


public class MyLibApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MyLibApplication.class, args);
        DBWorker dbWorker = new DBWorker();

        dbWorker.showAllRow();
        dbWorker.insertNewBook(5, "Карлсон", "Линдра", "Карлсон который живет на крыше", "C:\\icon.png");
        dbWorker.insertNewBook(NULL, "Мальчик с пальчик", "Перро", "Немецкая сказка", "C:\\icon.png");
        dbWorker.deleteBookById(5);
        dbWorker.updateBookDescription(10, "Сказка про мальчика");
        dbWorker.searchBookByNameOrAuthor("Гримм");
        dbWorker.insertNewBook(NULL, "Русалочка", "Андерсон", "Сказка про подставу", "");

    }
}
