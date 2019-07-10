package com.shivali.familytree.relationships;

import java.util.List;
import java.util.stream.Collectors;
import com.shivali.familytree.*;

import static com.shivali.familytree.TreeHelper.*;

public class SisterInLaw implements IRelationShip {
    Family root;

    public SisterInLaw(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family family = getParentFamily(personName, root);
        if (isBornChildrenOf(family, personName)) {
            return getSiblingsFamily(root, personName).stream().map(f -> f.spouse).collect(Collectors.toList());
        } else {
            Family personFamily = searchFamilyOf(personName, root);
            List spouseSiblings = getSiblings(root, getSpouse(personName, personFamily).getName());
            return filterByGender(GenderType.Female, spouseSiblings);
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
