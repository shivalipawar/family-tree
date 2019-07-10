package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;

import java.util.List;

import static com.shivali.familytree.TreeHelper.filterByGender;
import static com.shivali.familytree.TreeHelper.searchFamilyOf;

public class Daughter implements IRelationShip {
    Family root;

    public Daughter(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family childsFamily = searchFamilyOf(personName, root);
        List<Person> children = childsFamily.getBornChildren();
        return filterByGender(GenderType.Female, children);
    }
}
