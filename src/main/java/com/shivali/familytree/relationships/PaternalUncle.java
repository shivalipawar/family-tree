package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.filterByGender;
import static com.shivali.familytree.TreeHelper.getParentFamily;

public class PaternalUncle implements IRelationShip {
    Family root;

    public PaternalUncle(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        Sibling sibling = new Sibling(root);
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
            List siblingsOfFather = sibling.getPersons(parentOfPerson.bornChild.getName());
            return filterByGender(GenderType.Male, siblingsOfFather);
        } else {
            return new ArrayList<>();
        }
    }
}
