package com.company;

public class StringConst {
    public String newLine=System.getProperty("line.separator");;

    public final String defaultstring="Не могу вас понять("+newLine+
            "Возможно вы используете продвинутые команды до полноценного создания питомца "+newLine+
            "или /start вместо /rollback при уже созданном питомце";

    public final String help="Добро пожаловать в чат общения с вашим питоцем." +newLine+
            "Используйте команду /start, чтобы завести питомца" + newLine+
            "А затем заботьтесь о нем командами /caress и /feed" + newLine+
            "Если вы будете хорошим хозяином, то увидите как он растет и учится новому)" +newLine+
            "Не забывайте о нем, ведь без вас он погибнет!";
    public final String rollback = "используйте /start, чтобы завести питомца снова";
    public final String  genderChoice = "Выберите пол вашего питомца";
    public final String nameChoice = "Как будут звать вашего питомца?";
    public final String feed = "ням-ням";

    public String getFullPetString(String name, String gender){
        return "Теперь у вас есть питомец-"+gender+"!Его(ее) имя "+name;
    }

    public String getAdmireString(String name, String gender){
        return "Ваш питомец-"+gender+"!Его(ее) имя "+name;
    }

    public String getRenameString(String name){
        return "Сейчас Вашего питомца зовут "+name+". Введите новое имя";
    }
}
