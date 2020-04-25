/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notebook;

import java.util.Locale;

/**
 *
 * @author kaa
 */
public class Language {
    String lang;
    final String RUS="русский";
    public Language(){
        this.lang=Locale.getDefault().getDisplayLanguage();
    }
    boolean isRus(){
        return lang.equals(RUS);
    }
    String forNewButton(){
        if(isRus())
            return "НОВАЯ";
        else
            return "NEW";
    }
    String forDeleteButton(){
        if(isRus())
            return "УДАЛИТЬ";
        else
            return "DELETE";
    }
    String forSaveButton(){
        if(isRus())
            return "СОХРАНИТЬ";
        else
            return "SAVE";
    }
    String messageSave(int n){
        switch(n){
            case 1:
                if(isRus())
                    return "Выберите заметку из списка слева.\nЕсли список пуст, то создайте новую заметку.";
                else
                    return "Select a note from the list on the left.\nIf the list is empty, create a new note.";
                case 2:
                    if(isRus())
                        return "Сохранено в буфер обмена";
                    else
                        return "Saved to clipboard";
                    case 3:
                        if(isRus())
                            return "Ничего нет для сохранения";
                        else
                            return "Nothing to save";
                        case 4:
                            if(isRus())
                                return "Сохранено";
                            else
                                return "Saved";
                            case 5:
                                if(isRus())
                                    return "Ошибка";
                                else
                                    return "Error";
                                default:
                                    return "";
        }
    }
    String messageDelete(){
        if(isRus())
            return "Ошибка удаления";
        else
            return "Delete failed";
    }
    String forExitWindow(int n){
        switch(n){
            case 1:
                if(isRus())
                    return "ВЫХОД";
                else
                    return "EXIT";
                case 2:
                    if(isRus())
                        return "Выйти из программы?";
                    else
                        return "Quit the program?";
                    case 3:
                        if(isRus())
                            return "Несохраненные изменения будут потеряны навсегда.";
                        else
                            return "Unsaved changes will be permanently lost.";
                        default:
                            return "";
        }
    }
    String forAlertWindow(){
         if(isRus())
            return "Внимание!";
        else
            return "Attention!";
    }
    String forDateAndTime(int  n){
         switch(n){
            case 1:
                if(isRus())
                    return "Вс.";
                else
                    return "Sun.";
                case 2:
                    if(isRus())
                        return "Пн.";
                    else
                        return "Mon.";
                    case 3:
                        if(isRus())
                            return "Вт.";
                        else
                            return "Tues.";
                        case 4:
                            if(isRus())
                                return "Ср.";
                            else
                                return "Wed.";
                            case 5:
                                if(isRus())
                                    return "Чт.";
                                else
                                    return "Thurs.";
                                case 6:
                                    if(isRus())
                                        return "Пт.";
                                    else
                                        return "Fri.";
                                    case 7:
                                        if(isRus())
                                            return "Сб.";
                                        else
                                            return "Sat.";
                                        default:
                                            return "";
        }
    }
    String forDirectoryCreator(int n){
        switch(n){
            case 1:
                if(isRus())
                    return "Создан каталог <MyNoteBook>.\nВаши заметки будут здесь:\n";
                else
                    return "The <MyNoteBook> directory has been created.\nYour notes will be here:\n";
                case 2:
                    if(isRus())
                        return "Ошибка!\nКаталог <MyNoteBook> не будет создан, а программа будет закрыта."
                                + "\nПопробуйте создать указанный каталог по следующему пути:\n";
                    else
                        return "Error!\nThe <MyNoteBook> directory will not be created, and the program will be closed."
                        + "\nTry to create the specified directory in the following path:\n";
                    default:
                        return "";
        }
    }
    String permission(int n){
        switch(n){
            case 1:
                if(isRus())
                    return "Не удалось получить разрешение на чтение и запись файлов в каталог <MyNoteBook>.\nПопробуйте дать разрешение вручную.";
                else
                    return "Failed to get permission to read and write files to <MyNoteBook> directory.\nTry to give the permission manually.";
                case 2:
                    if(isRus())
                        return "Не удалось получить разрешение на чтение файлов в каталоге <MyNoteBook>.\nПопробуйте дать разрешение вручную.";
                    else
                        return "Could not get permission to read files in the <MyNoteBook> directory.\nTry to give the permission manually.";
                    case 3:
                        if(isRus())
                            return "Не удалось получить разрешение на запись файлов в каталог <MyNoteBook>.\nПопробуйте дать разрешение вручную.";
                        else
                            return "Failed to get permission to write files to <MyNoteBook> directory.\nTry to give the permission manually.";
                        default:
                            return "";
        }
    }
    String nameApplication(){
        if(isRus())
            return "Блокнот";
        else
            return "Notepad";
    }
}
