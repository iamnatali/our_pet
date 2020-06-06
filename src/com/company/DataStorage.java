package com.company;

import java.sql.SQLException;
import java.util.HashMap;

public interface DataStorage {
    void deleteData(Long index);

    void updateData(Long index, PetBot pet);

    //Boolean-найден ли индекс
    HashMap<PetBot, Boolean> getData(Long index);

    void setData(Long index, PetBot pet);
}
