
import java.util.ArrayList;
import java.util.List;




public class Controller {
    Model model = new Model();
    View view = new View();
    Count count = new Count(); 
    Animals animal = new Animals();
    Animcomm animcomm = new Animcomm();
   
   String fileAnimal = "animals.txt"; //Каталог животных
   String fileCommands = "commands.txt"; 
   String fileAnimCom = "animcomm.txt"; 
   String fileViewName = "animalview.txt";
    /**
     * Основная функция программы.
     */
    public void runController() {
      
        List <Animals> animalIn = new ArrayList<>(); //Список для каталога игрушек
        List <String> listAnimCom = new ArrayList<>(); // Список из файла соответствия команд животным
        List <String> viewAnimals = new ArrayList<>(); //Для хранения списка разыгранных игрушек
        List <String>  listOfCommand = new ArrayList<>(); //Для хранения выданных игрушек
        List<String> listAnimCommands = new ArrayList<>();//упорядоченный по животным список команд
    
     animalIn = model.getAnimalList(fileAnimal);
     

     count.setAnimalCnt(animalIn.size()); // установка счетчика
     listAnimCom = model.getListfromFile(fileAnimCom);
     viewAnimals = model.getListfromFile(fileViewName);
     listOfCommand = model.getListfromFile(fileCommands);
     listAnimCommands = model.listNamedCmnd(listOfCommand,listAnimCom);
     

        Integer choice;

    do {
        System.out.println("Выберите действие:");
        System.out.println("1. Показать список-реестр животных");
        System.out.println("2. Завести новое животное");
        System.out.println("3. Удалить животное");
        System.out.println("4. Список доступных команд для животного");
        System.out.println("5. Обучить животное новой команде");
        System.out.println("0. Сохранение и Выход");
        
        choice = view.getChoice("Введите номер операции: ");
       
    switch (choice) {
        case 1:
             view.displayResult(animalIn);
             System.out.println("Общее количество записей: " + count);
            break;
        case 2:
            String nextNum = model.searchNextNum(animalIn);
            animal = view.addViewName( view.inviteForAddAniml(
                nextNum),viewAnimals);
            listAnimCommands.add("");
            count.setAnimalCnt(animalIn.size());
            try{
                count.add(animal, animalIn, count);
            }catch (NullExeptionAnimal ex){
                System.out.println(ex.getMessage());
                System.out.println();
            }
            break;
        case 3:
            view.displayResult(animalIn);
            Integer chInt = view.getChoice("Введите ID позиции которую хотите удалить: ");
            if (chInt > 0 && model.enterCheck(chInt, animalIn)) {
                model.deleteAnimPosition(animalIn, chInt);
                int tmp = model.searchPos(animalIn, choice);
                listAnimCommands.remove(tmp);
                count.setAnimalCnt(animalIn.size());
            }
            break;
        case 4:
        view.displayResult(animalIn);
        int index = model.searchPos(animalIn, 
                    view.getChoice
                    ("Введите индекс животного, команды которого вы хотите посмотреть: "));
       
        view.showCommAnim(animalIn.get(index),
                    model.listNamedCmnd(listOfCommand,listAnimCom).get(index));
            break;
        case 5:
            view.displayResult(animalIn);
            Integer chInt1 = view.getChoice ("Введите индекс животного, которого вы хотите обучить: ");
            int index1 = 0;
            int index2 = 0;
            if (chInt1 > 0 && model.enterCheck(chInt1, animalIn)) {
            index1 = model.searchPos(animalIn, chInt1);
            view.showCommAnim(animalIn.get(index1),listAnimCommands.get(index1));
            
            System.out.println("Команды доступные для обучения: ");
            List <String> commToLearn =  model.listComTolearn(listAnimCommands.get(index1),listOfCommand);             
            System.out.println(commToLearn);
            chInt1 = view.getChoice
            ("Введите индекс команды которой вы хотите обучить выбранное животное: ");
                if (chInt1 > 0 && model.enterCheckLstLst(chInt1, (commToLearn))){
                    index2 = model.searchPoslst(listOfCommand, chInt1); 
                    System.err.println(index2); 
                    listAnimCommands.set(index1, model.addCmndforAnim(listOfCommand.get(index2),
                                                listAnimCommands.get(index1)));
            view.showCommAnim(animalIn.get(index1),listAnimCommands.get(index1)); 
            }  
        } 
            break;
        case 0:
            System.out.println("****************************");
        model.writeList(animal.lstAnimToString(animalIn), fileAnimal); //Запись в файл реестра животных
        model.writeList((model.lstCmndforWriteTofile(listAnimCommands,animalIn)),fileAnimCom); //Запись команд животных
        System.out.println("Программа завершена.");
            break;
        default:
        System.out.println("Неверный выбор. Попробуйте еще раз.");
            break;
        }
        } while (choice != 0);
        
    }
}

