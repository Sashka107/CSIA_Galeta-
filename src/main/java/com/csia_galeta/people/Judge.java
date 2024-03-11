package com.csia_galeta.people;

/**
 * Class Judge
 * Этот класс описывает поведение и что содержит в себе судья для правильной
 * и полноценной работоспособности в этой программе
 *
 * @author Alexander G.
 */
public class Judge {

    private String judgeName; // переменная хранящая имя
    private String judgeSurname; // переменная хранящая фамилию

    /**
     * Метод setter для установки имени
     *
     * @return true - если имя успешно прошло проверку и установлено, false - если нет
     * @param name - имя, которое должно быть установлено
     */
    public boolean setName (String name){

        // если имя соответствует требованиям от 1 до 55 символов
        if (name.matches("^.{1,55}$")) {
            this.judgeName = name;
            return true;
        }

        // если не соответствует
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    /**
     * Getter for judge`s name
     *
     * @return name of the judge
     */
    public String getName (){
        return judgeName;
    }

    /**
     * Метод setter для установки фамилии
     *
     * @return true - если фамилия успешно прошло проверку и установлено, false - если нет
     * @param surname - фамилия, которая должна быть установлена
     */
    public boolean setSurname (String surname){

        // если фамилия соответствует требованиям от 1 до 55 символов
        if (surname.matches("^.{1,55}$")) {
            this.judgeSurname = surname;
            return true;
        }

        // если не соответствует
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    /**
     * Getter for judge`s lastname
     *
     * @return lastname of the judge
     */
    public String getSurname (){
        return judgeSurname;
    }

    /**
     * Метод собирает информациюю о судье и возвращает её в формате строки
     *
     * @return вся нужная информация о судье в виде строки
     */
    @Override
    public String toString() {
        // получаем первую букву имени и делаем её в верхний регистр
        char firstLetterName = Character.toUpperCase(judgeName.charAt(0));

        // возвращаем в формате: "Name L"
        return judgeName.replace(judgeName.charAt(0), firstLetterName) + " " + Character.toUpperCase(judgeSurname.charAt(0));
    }
}
