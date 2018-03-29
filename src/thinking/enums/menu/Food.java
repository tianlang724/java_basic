package thinking.enums.menu;
public interface Food{
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGEN,BURRITO,PAD_THAT;
    }
    enum Dessert implements Food{
        FRUIT,GELATO,VINDALOO;
    }
    enum Coffee implements Food{
        BLACK_COFFEE,TEA,HRAB_TEA;
    }
}