package CodingGuide.StackAndQueue;

import java.util.LinkedList;

public class CatAndDogQueue {
    LinkedList<PetElement> cats=new LinkedList<>();
    LinkedList<PetElement> dogs=new LinkedList<>();
    private static int num;

}
class Pet{
    private  String type;
    public Pet(String type){
        this.type=type;
    }
}
class Dog extends Pet{

    public Dog() {
        super("Dog");
    }
}
class Cat extends Pet{

    public Cat() {
        super("Cat");
    }
}
class PetElement{
    private Pet pet;
    private int sequeueNum;
    public  PetElement(Pet pet,int sequeueNum){
        this.pet=pet;
        this.sequeueNum=sequeueNum;
    }
}