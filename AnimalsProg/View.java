import java.util.Scanner;
import java.util.List;
    
public class View {
    
    Scanner scanner; 

    /**
    * Приглашение к вводу нового животного и 
    * запрос на определение класса животного (вьючное или домашнее)
    * @return Animals
    */
    public Animals inviteForAddAniml(String nextNum){
   
        Animals animlh = new Animalhome();
        Animals animlp = new Animalpack();
        Animals animl = new Animals();
        System.out.println();
        
        System.out.println("Выберите, какого типа животное вы хотите добавить:");
        System.out.println();
        switch (getChoice("1 - home (домашнее)  2 - pack (вьючное) ?")) {
        case 1:
        System.out.println("Вы выбрали домашнее (home) животное.");
            animlh.setAnimalId(nextNum);
            return animlh; 
        case 2:
            System.out.println("Вы выбрали вьючное (pack) животное.");
            animlp.setAnimalId(nextNum);
            return animlp;  
        default:
        System.out.println("Будет установлено значение null");
            return animl;      
        }
    }

    /**
     * Распечаывает список парами построчно
     * @param list
     */
    public void prntLstByPair(List <String> list){
        int cnt = list.size();
        for (int i = 0; i < cnt; i+=2) {
            if ((i+1) < cnt )
            System.out.println(list.get(i) + " " + list.get(i+1));
            else
            System.out.println(list.get(i));
        }  
    }

    /**
    * Заполняет поля "вид" и "кличка" экземпляра класса Animals 
    * @param animal
    * @param viewaniml
    * @return
    */
    public Animals addViewName(Animals animal, List <String> viewaniml){
    System.out.println();
    
    prntLstByPair(viewaniml);
     
    Integer ch = getChoice("Выберите номер вида животного: ");
    if (ch < 1 && ch > viewaniml.size()-1) {
        System.out.println("Значение вне диапазона. В данном случае установлено значение NULL");
        System.out.println();
    }
    //Берем строку в соответсвии с выбором и из нее значение после ";" 
    animal.setAnimalview(viewaniml.get(ch).substring(viewaniml.get(ch).lastIndexOf(";")+1));
    System.out.println();
    String tmpstr = getUserInput("Введите кличку животного: ");

    if (tmpstr == "") {
        System.out.println("Введена пустая строка. Установлено значение NULL");
        animal.setAnimalName(null);
        } 
    else animal.setAnimalName(tmpstr);
    return animal;
    }

    /**
    * Выводит список элементов класса Animals
    * @param animal
    */
    public void displayResult(List <Animals> animal){
        System.out.println("************************");
        System.out.println("Список-реестр животных: ");
        System.out.println("ID"+" "+" Тип"+" "+" Вид"+" "+"Кличка");
        System.out.println("------------------------");
        for (Animals item : animal) {
            System.out.println(item);
        }
        System.out.println("************************");
    } 


        /**
         * Метод ввода с консоли - возвращает Integer
         * @param prompt
         * @return
         */
        public Integer getChoice(String prompt) {
            System.out.print(prompt);
            Integer tmpVal = -1;
            scanner = new Scanner(System.in);
        try {   
            tmpVal = scanner.nextInt();
            return tmpVal;
        }catch (Exception e) {
            System.out.println("Введено не корректное значение.");
            return tmpVal;
            }  
        }
        /**
        * С консоли возвращает - String
        * @param prompt
        * @return
        */   
        public String getUserInput(String prompt) {
            System.out.print(prompt);
            String tmpVal= "";
            scanner = new Scanner(System.in, "cp866");
        try {   
         tmpVal = scanner.nextLine(); 
         return tmpVal;
        }catch (Exception e) {
            return tmpVal;
        }  
        }

        /**
         * Отображение команд для конкретного животного
         * @param animl
         * @param commands
         */
        public void showCommAnim(Animals animl, String commands){  
            switch (commands.length()) {
                case 0:
                    System.out.println(animl.getAnimalview()+ " по кличке "+ 
                    animl.getAnimalName() + " ничему не обучен(");
                    System.out.println();
                    break;
                case 1:
                    System.out.println(animl.getAnimalview()+ " по кличке "+ 
                    animl.getAnimalName() + " обучен команде: ");
                    System.out.println(commands);
                    System.out.println();
                    break;
                default:
                    System.out.println(animl.getAnimalview()+ " по кличке "+ 
                    animl.getAnimalName() + " обучен командам: ");
                    System.out.println(commands);
                    System.out.println();
                    break;
            }                      
        }
    } 

