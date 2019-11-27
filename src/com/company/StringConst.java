package com.company;

class StringConst {
    private String newLine=System.getProperty("line.separator");

    final String defaultstring="Не могу вас понять("+newLine+
            "Возможно вы используете продвинутые команды до полноценного создания питомца "+newLine+
            "или /start вместо /rollback при уже созданном питомце";

    final String help="Добро пожаловать в чат общения с вашим питоцем." +newLine+
            "Используйте команду /start, чтобы завести питомца" + newLine+
            "А затем заботьтесь о нем командами /caress и /feed" + newLine+
            "Если вы будете хорошим хозяином, то увидите как он растет и учится новому)" +newLine+
            "Не забывайте о нем, ведь без вас он погибнет!";
    final String rollback = "используйте /start, чтобы завести питомца снова";
    final String  genderChoice = "Выберите пол вашего питомца";
    final String nameChoice = "Как будут звать вашего питомца?";
    final String feed = "ням-ням";

    String getFullPetString(String name, String gender){
        return "Теперь у вас есть питомец-"+gender+"!Его(ее) имя "+name;
    }

    String getAdmireString(String name, String gender, int wealth){
        return "Ваш питомец-"+gender+"!Его(ее) имя "+name+" его уровень радости: "+wealth ;
    }

    String getRenameString(String name){
        return "Сейчас Вашего питомца зовут "+name+". Введите новое имя";
    }
}
