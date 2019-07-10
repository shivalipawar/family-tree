package com.shivali.familytree.relationships;

import java.util.ArrayList;
import java.util.List;

import com.shivali.familytree.*;
import static com.shivali.familytree.TreeHelper.filterByGender;
import static com.shivali.familytree.TreeHelper.getParentFamily;

public class PaternalAunt implements IRelationShip {
    Family root;

    public PaternalAunt(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        Sibling sibling = new Sibling(root);
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
            List siblingsOfFather = sibling.getPersons(parentOfPerson.bornChild.getName());
            return filterByGender(GenderType.Female, siblingsOfFather);
        } else {
            return new ArrayList<>();
        }

    }
}
