package com.shivali.familytree;

import com.shivali.familytree.util.Either;

import java.util.List;
import java.util.stream.Collectors;

import static com.shivali.familytree.Constants.PERSON_NOT_FOUND;

public class TreeHelper {

    void addChildToTree(String parentName, String childName, GenderType childGender, Family rootFamily) throws CustomException {
        Family familyNode = searchFamilyOf(parentName, rootFamily);
        if (!isFemaleInFamily(familyNode, parentName))
            throw new CustomException(Constants.CHILD_ADD_FAILURE);
        familyNode.addChild(Either.right(new Person(childName, childGender)));
        System.out.println(Constants.CHILD_ADD_SUCCESS);
    }

    private boolean isFemaleInFamily(Family familyNode, String motherName) {
        return (familyNode.bornChild.getName().equals(motherName) && familyNode.bornChild.getGender() == GenderType.Female) ||
                (familyNode.spouse.getName().equals(motherName) && familyNode.spouse.getGender() == GenderType.Female);

    }

    public static Family searchFamilyOf(String personName, Family root) throws CustomException {
        if (root.spouse.getName().equals(personName) || root.bornChild.getName().equals(personName))
            return root;
        Family result = getFamilyOf(personName, root);
        if (result != null) return result;
        throw new CustomException(PERSON_NOT_FOUND + " : " + personName);
    }

    private static Family getFamilyOf(String personName, Family root) {
        if (root.spouse.getName().equals(personName) || root.bornChild.getName().equals(personName))
            return root;
        for (Either<Family, Person> child : root.children)
            if (child.isLeft()) {
                Family family = getFamilyOf(personName, child.getLeft());
                if (family != null) {
                    return family;
                }
            }
        return null;
    }

    static Family getParentOf(String personName, Family root) {
        for (Either<Family, Person> child : root.children) {
            if (child.isLeft()) {
                Family family = child.getLeft();
                if (family.contains(personName))
                    return root;
                Family parentFamily = getParentOf(personName, family);
                if (parentFamily != null) {
                    return parentFamily;
                }
            } else {
                if (personName.equals(child.getRight().getName())) return root;
            }
        }
        return null;
    }

    public static Family getParentFamily(String personName, Family root) throws CustomException {
        if (root == null) throw new CustomException(PERSON_NOT_FOUND + ":" + personName);
        Family result = getParentOf(personName, root);
        if (result == null) {
            throw new CustomException(PERSON_NOT_FOUND + ":" + personName);
        }
        return result;
    }

    public static List<Family> getSiblingsFamily(Family familyRootNode, String personName) throws CustomException {

        return getParentFamily(personName, familyRootNode).children.stream()
                .filter(familyOfPerson -> familyOfPerson.isLeft() && notOwnFamily(familyOfPerson.getLeft(), personName))
                .map(Either::getLeft).collect(Collectors.toList());
    }

    private static boolean notOwnFamily(Family family, String personName) {
        return !family.bornChild.getName().equals(personName) && !family.spouse.getName().equals(personName);
    }

    public static boolean isBornChildrenOf(Family family, String personName) {
        return !family.getBornChildren().stream().filter(c -> c.getName().equals(personName)).collect(Collectors.toList()).isEmpty();
    }

    public static List<Person> filterByGender(GenderType genderType, List<Person> children) {
        return children.stream().filter(t -> t.getGender().equals(genderType)).collect(Collectors.toList());
    }

    public static List getSiblings(Family familyRootNode, String personName) throws CustomException {
        Family parentfamilyOfGivenPerson = getParentFamily(personName, familyRootNode);
        List<Person> children = parentfamilyOfGivenPerson.getBornChildren();
        return children.stream().filter(child -> !child.getName().equals(personName)).collect(Collectors.toList());
    }
}
