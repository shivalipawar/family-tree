package com.shivali.familytree;

import com.shivali.familytree.util.Either;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FamilyTest {

    Family familyRootNode, bornChildFamily;
    Person born;
    Person spouse;
    Either<Family, Person> bornChild;

    @Before
    public void initialize() {
        bornChild = Either.right(new Person("Inka", GenderType.Female));
        born = new Person("Shan", GenderType.Male);
        spouse = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(born, spouse);
        bornChildFamily = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
    }

    @Test
    public void getBornChildShouldReturnListOfChildAsPerson() {
        familyRootNode.addChild(bornChild);
        familyRootNode.addChild(Either.right(bornChildFamily.bornChild));
        List<Person> bornChildren = familyRootNode.getBornChildren();
        Assert.assertEquals(bornChild.getRight(),bornChildren.get(0));
        Assert.assertEquals(bornChildFamily.bornChild,bornChildren.get(1));
    }
}