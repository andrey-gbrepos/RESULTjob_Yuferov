import java.util.List;
import java.util.ArrayList;


public class Animcomm {
    private Animals animal;
    private String commands;
    
    
    public List <Animcomm> animcomList (List<Animals>anim, List<String> command){
        List<Animcomm> animcommList = new ArrayList<>();
        if(anim.size() == command.size()){
            for (int i = 0; i < anim.size()-1; i++) {
                animcommList.get(i).setAnimal(anim.get(i));
                animcommList.get(i).setCommands(command.get(i));    
            }
        } else {
            System.out.println();
            System.out.println("Целостность текстовых файлов нарушена.");
            System.out.println("Количество записей в файлах 'animals.txt' и 'animcomm.txt' не совпадают.");
            System.out.println();
        }
        System.out.println(animcommList);
        return animcommList;
    }
    
    
    public Animals getAnimal() {
        return animal;
    }
    public void setAnimal(Animals animal) {
        this.animal = animal;
    }
    public String getCommands() {
        return commands;
    }
    public void setCommands(String commands) {
        this.commands = commands;
    }




}
