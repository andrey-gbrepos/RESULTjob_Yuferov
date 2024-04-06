import java.util.List;

public class Count {
  protected Integer  animalCnt;


    public void add(Animals anml, List <Animals> animals, Count cnt) 
                                            throws NullExeptionAnimal{
        System.out.println();
        if(anml.getAnimalId() == null || anml.getAnimalDest() == null ||
            anml.getAnimalview() == null || anml.getAnimalName() == null) 
            throw new NullExeptionAnimal(
            "Запись содержит значения null и не может быть добавлена в реестр.");
            
        animals.add(anml);
        cnt.setAnimalCnt(cnt.getAnimalCnt()+1);
        System.out.println();
        System.out.println("Запись " + anml + " добавлена в реестр"); 
        cnt.setAnimalCnt(animals.size());
        System.out.println("Общее количество записей " + cnt);
        System.out.println();
    }
    
    
    public Integer getAnimalCnt() {
        return animalCnt;
    }
    public void setAnimalCnt(Integer animalCnt) {
        this.animalCnt = animalCnt;
    }
   
    @Override
    public String toString() {
        return String.format("%d", animalCnt);
    }
}
