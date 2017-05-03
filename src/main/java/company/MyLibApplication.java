package company;

import company.dao.BookDao;

public class MyLibApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MyLibApplication.class, args);

        System.out.println(new BookDao().getAllBooks());

    }
}
