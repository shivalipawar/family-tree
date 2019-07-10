package com.shivali.familytree.relationships;

import com.shivali.familytree.*;
import java.util.List;

import static com.shivali.familytree.TreeHelper.searchFamilyOf;

public class Son implements IRelationShip {
    Family root;

    public Son(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family childsFamily = searchFamilyOf(personName, root);
        return TreeHelper.filterByGender(GenderType.Male, childsFamily.getBornChildren());
    }
}
