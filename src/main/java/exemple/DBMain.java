package exemple;

import com.company.model.Book;
import com.company.service.ModelService;

public class DBMain {
    public static void main(String[] args){
        ModelService modelService = new ModelService();


//        Book book1 = new Book();
//        book1.setPriceInEuros(300.0);
//        book1.setNameOfTheBook("The Last Wish");
//        book1.setAuthorName("Andrzew Sapkowski");
//
//        modelService.updateBookInInventory(book1);

        modelService.deleteBookInInventory("Book","PRICEINEUROS=12");

    }
}
