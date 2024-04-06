

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    FileStream fileStream = new FileStream();
    Random random = new Random();
    /**
     * Создает список строк из файла
     * @param fileName
     * @return
     */
    public List <String> getListfromFile(String fileName){
        List <String> list = fileStream.readfile(fileName);
        return list;
    }

    /**
     * Записывает список строк в файл
     * @param str
     * @param fileName
     */
    public void writeList (List <String> str, String fileName) {
        if(!fileStream.writeListTofile(str, fileName)){
            System.out.println("Возникли проблемы при записи в файл.");
        }  
    }


    /**
     * Создает список класса Animals из файла
     * @param fileName
     * @return
     */
    public List <Animals> getAnimalList(String fileName){   
        List <String> animList =  fileStream.readfile(fileName);
        List <Animals> animls = new ArrayList<>();
        for (String item : animList) {
            Animals anim = new Animals();
            String[] elements = item.split(";");
            anim.setAnimalId(elements[0]);
            anim.setAnimalDest(elements[1]);
            anim.setAnimalview(elements[2]);
            anim.setAnimalName(elements[3]);
            animls.add(anim);     
        }
        return animls;
    }
/**
 * Ищет следующий порядковый номер в нумерованном списке  объектов Animals.
 * Возвращает число в строковом представлении.
 * @param lst
 * @return
 */
    public String searchNextNum(List <Animals> lst){
        Integer max = 0;
        for (int i = 0; i < lst.size(); i++) {
            Integer tmp = Integer.parseInt(lst.get(i).getAnimalId());
            if(tmp>max) max = tmp;
        }
        String newId = ((Integer)(max+1)).toString();
        return newId;
    }
/**
     * Возвращает позицию в списке по заданном числу с клавиатуры
     * В случае неудачи возвращает -1.
     * @param animals
     * @param animalId
     * @return
     */
    public Integer searchPos (List <Animals> animals, Integer animalId){
        int index = -1;
        for (int i = 0; i < animals.size(); i++) {
                if(animals.get(i).getAnimalId().equals(animalId.toString())){ 
                    index = i;  
        }}
        if(index == -1) System.out.println("Записи с ID " + animalId + " не найдено.");
        return index;
    }

    public Integer searchPoslst (List <String> lst, Integer choice){
         int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            String[] elem = lst.get(i).split(";"); 
                for (int j = 0; j < elem.length; j++) {
                    if(elem[j].equals(choice.toString())) index = i;  
            }   
        }         
        if(index == -1) System.out.println("Записи с ID " + choice + " не найдено.");
        return index;
    }
    /**
     * Возвращает список с видами животных.
     * @param fileName
     * @return
     */
    public List <String> getViewList(String fileName){   
        List <String> viewList =  fileStream.readfile(fileName);
        for (String item : viewList) {
            String[] elements = item.split(";");
            viewList.add(elements[0]);
            viewList.add(elements[1]);       
        }
    System.out.println(viewList);
        return viewList;
    }

    /**
     * Возвращает именованный список команд в порядке списка в файле
     * @param fileCommands
     * @param fileAnimCom
     * @return
     */
    public List<String> listNamedCmnd(List<String> listCommands, List<String> listAnimCom){
        List<String> listNamedCom = new ArrayList<>();
        String strtemp = "";
        for (int i = 0; i < listAnimCom.size(); i++) {
            String[] elem1 = listAnimCom.get(i).split(";");
            for (int j = 1; j < elem1.length; j++) { 
                for (int k = 0; k < listCommands.size(); k++) {
                    String[] elem2 = listCommands.get(k).split(";");
                    if(elem1[j].equals(elem2[0]))
                    strtemp += listCommands.get(k)+" ";
            }
        }
        listNamedCom.add(strtemp);
            strtemp = "";
        }       
        return listNamedCom;
    }
    /**
     * Готовит список команд животных для записи в файл
     * @param List<String> lstNmdCmnd 
     * @param List<Animals> lstAnimal
     * @return
     */
    public List<String> lstCmndforWriteTofile(List<String>lstNmdCmnd, List<Animals> lstAnimal){
        List<String> lstForOut = new ArrayList<>();
        String strTmp = "";
        String result = "";
        if(lstNmdCmnd.size() == lstAnimal.size()){
            for (int i = 0; i < lstAnimal.size(); i++) {
                String[] elem = lstNmdCmnd.get(i).split(" ");
                result += lstAnimal.get(i).getAnimalId();
                for (int j = 0; j < elem.length; j++) {
                    strTmp = elem[j]; 
                    String[] elem1 = strTmp.split(";");
                    result += ";"+elem1[0];
                }
                lstForOut.add(result);
                result = "";
               
            }
        }
        return lstForOut;
    }

    /**
     * Возвращает список команд доступных для обучения
     * @return
     */
    public List<String> listComTolearn (String listAnimCom, List<String> commands){
        List<String> resList = new ArrayList<>();
        for (String item : commands) {
            resList.add(item);
        }
        String[] element = listAnimCom.split(" ");
       // for (int i = 0; i < element.length; i++) {
         //   System.out.println(String.format("%s",element[i]));
        //}
        for (int i = 0; i < resList.size(); i++) {
            for (int j = 0; j < element.length; j++) {
                if(element[j].equals(resList.get(i))) resList.remove(i);
            }
    }
        return resList;
    }
/**
 * Добавляет (обучает) новую команду для животного
 * @param cmnd
 * @param cmndOfAnim
 * @return
 */
    public String addCmndforAnim (String cmnd, String cmndOfAnim){
        
        String[] elem = cmndOfAnim.split(";");
        int st = 0;
        for (int i = 0; i < elem.length; i++) {
            if(elem[i].equals(cmnd)) st--; 
        }   
        if(st < 0)  System.out.println(    
        "Эта команда уже была раннее доступна. Или введено некорректное значение" +"\n"+"Обучения новой команде не произошло");
           else{ 
            cmndOfAnim += cmnd;
            
            System.out.println("Команда "+cmnd +" добавлена");
           }
           return cmndOfAnim;
    }

    /**
     * Метод для поиска команд доступных конкретному животному
     * @param animl
     * @param fileCommands -файл со списком команд
     * @param fileAnimCom - файл со списком соотношения зверей и команд
     */
    public List<String> listCommAnim(String idAnimal, String fileCommands, String fileAnimCom){
        List <String> listCommands = new ArrayList<>();
        List <String> listAnimCom = new ArrayList<>();
        listCommands = getListfromFile(fileCommands);
        listAnimCom = getListfromFile(fileAnimCom);
       
        List<String> readyListCom = new ArrayList<>();
     
        String tempLst = "";
        
            for (int i = 0; i < listAnimCom.size(); i++){
                String[] elem = listAnimCom.get(i).split(";");
                if (elem.length > 1) //Первый элемент в строке файла -> индекс животного
                    if (idAnimal.equals(elem[1])) tempLst = listAnimCom.get(i);
            }
                String[] elements = tempLst.split(";");
                
                for (int k = 0; k < listCommands.size(); k++){  
                    String[] element = listCommands.get(k).split(";");
                    for (int j = 1; j < elements.length; j++) {
                    if (elements[j].equals(element[0])) readyListCom.add(listCommands.get(k));
                }
            }            
            System.out.println();
            return readyListCom;
        }

    
    public void deleteAnimPosition(List <Animals> animal, Integer idAnimal){
        int index = searchPos(animal, idAnimal);
        if (index != -1) {
        System.out.println("****************************");
        System.out.println("Позиция "+ animal.get(index)+ " удалена из каталога.");
        System.out.println("****************************");
        animal.remove(index);
        }   
        }
    /**
    * Проверяет корректность введенного значения
    * @param choice
    * @param animal
    * @return
    */
        public boolean enterCheck (Integer choice, List <Animals> animal){
            boolean status = false;
            for (Animals elem : animal) {
                if(choice.toString().equals(elem.getAnimalId())) status = true;
            }
            if (!status) System.out.println("Введенное значение некорректно.");
            return status;
        }

        public boolean enterCheckLstLst (Integer choice, List <String> list){
            boolean status = false;

            for (int i = 0; i < list.size(); i++) {
                String[] elem = list.get(i).split(";"); 
                    for (int j = 0; j < elem.length; j++) {
                        if(elem[j].equals(choice.toString())){ 
                            status = true;
                    }
                    
            }
        }   
            if (!status) System.out.println("Введенное значение некорректно.");
            return status;
        }

    }
