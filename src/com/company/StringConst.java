package com.company;

public class StringConst {
    private String newLine;

    public final String defaultstring="Не могу вас понять("+newLine+
            "Возможно вы используете команды до полноценного создания питомца"+newLine+
            "начните с команды /start";

    public final String help="Добро пожаловать в чат общения с вашим питоцем." +newLine+
            "Используйте команду /start, чтобы завести питомца" + newLine+
            "А затем заботьтесь о нем командами /caress и /feed" + newLine+
            "Если вы будете хорошим хозяином, то увидите как он растет и учится новому)" +newLine+
            "Не забывайте о нем, ведь без вас он погибнет!";
    public final String rollback = "используйте /start, чтобы завести питомца снова";
    public final String  genderChoice = "Выберите пол вашего питомца";
    public final String nameChoice = "Как будут звать вашего питомца?";

    public StringConst(){
        newLine=System.getProperty("line.separator");
    }
}
