import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс "Животные"
 */
public class Animals {
    protected String animalId;
    protected String animalName;
    protected String animalview;
    protected String animalDest;

    public Animals() {
        this.animalId = null;
        this.animalName = null;
        this.animalview = null;
        this.animalDest = null;
    }
    public String getAnimalId() {
        return animalId;
    }
    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }
    public String getAnimalName() {
        return animalName;
    }
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalview() {
        return animalview;
    }
    public void setAnimalview(String animalview) {
        this.animalview = animalview;
    }

    public String getAnimalDest() {
        return animalDest;
    }
    public void setAnimalDest(String animalDest) {
        this.animalDest = animalDest;
    }

    public List<String> lstAnimToString(List<Animals>animToString){
        List <String> lstAnimString = new ArrayList<>();
        for (int i = 0; i < animToString.size(); i++) {
            lstAnimString.add(animToString.get(i).getAnimalId()+";"+
                            animToString.get(i).getAnimalDest()+";"+
                            animToString.get(i).getAnimalview()+";"+
                            animToString.get(i).getAnimalName());
        }
        return lstAnimString;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s;%s;%s;%s", animalId, animalDest, animalview, animalName);
        //return animalId + ";" + animalDest + ";" + animalview + ";" + animalName;
    }
}