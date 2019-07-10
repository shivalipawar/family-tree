package com.shivali.familytree.relationships;

import java.util.List;
import java.util.stream.Collectors;
import com.shivali.familytree.*;

public class BrotherInLaw implements IRelationShip {

    Family root;

    public BrotherInLaw(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family personsFamily = TreeHelper.getParentFamily(personName, root);
        if (TreeHelper.isBornChildrenOf(personsFamily, personName)) {
            return TreeHelper.getSiblingsFamily(root, personName)
                    .stream().map(person -> person.spouse)
                    .collect(Collectors.toList());
        } else {
            Person spouse = getSpouse(personName, TreeHelper.searchFamilyOf(personName, root));
            List spouseSiblings = TreeHelper.getSiblings(root, spouse.getName());
            return TreeHelper.filterByGender(GenderType.Male, spouseSiblings);
        }

    }

    private Person getSpouse(String personName, Family personFamily) {
        if (personFamily.bornChild.getName().equalsIgnoreCase(personName)) {
            return personFamily.spouse;
        } else {
            return personFamily.bornChild;
        }
    }
}
