package com.csia_galeta;

/**
 * Class Date
 * Этот класс предполагался как вспомогательный для хранения даты соревнования
 *
 * @author Alexander G.
 */
public class Date {
    public byte day; // переменная для хранения дня
    public byte month; // переменная для хранения месяца
    public byte year; // переменная для хранения года

    /**
     * Getter для получения даты в удобном для понимания формате
     *
     * @return дату в формате строки
     */
    public String getDate (){
        return this.day + "/" + this.month + "/" + this.year;
    }
}
