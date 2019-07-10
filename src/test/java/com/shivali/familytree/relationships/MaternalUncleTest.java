package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;
import com.shivali.familytree.util.Either;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MaternalUncleTest {
    Family familyRootNode, level1Family1, level1Family2, level1Family3, level1Family4, level2Family1, level2Family3, level2Family4;
    Person level1PersonChild1, father, mother, level2PersonChild1, level2PersonChild2, level3PersonChild1;
    MaternalUncle maternalUncle;

    @Before
    public void initialize() {
        level1PersonChild1 = new Person("Ish", GenderType.Male);
        level1Family1 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        level1Family2 = new Family(new Person("Vich", GenderType.Male), new Person("Lika", GenderType.Female));
        level1Family3 = new Family(new Person("Aras", GenderType.Male), new Person("Chitra", GenderType.Female));
        level1Family4 = new Family(new Person("Satya", GenderType.Female), new Person("Vyan", GenderType.Male));
        level2Family1 = new Family(new Person("Dritha", GenderType.Female), new Person("Jaya", GenderType.Male));
        level2Family3 = new Family(new Person("Asva", GenderType.Male), new Person("Satvy", GenderType.Female));
        level2Family4 = new Family(new Person("Vyas", GenderType.Male), new Person("Krip", GenderType.Female));
        level2PersonChild1 = new Person("Tritha", GenderType.Female);
        level2PersonChild2 = new Person("Vritha", GenderType.Male);
        level3PersonChild1 = new Person("Yodhan", GenderType.Male);
        father = new Person("Shan", GenderType.Male);
        mother = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(father, mother);
        //Added children to root family.
        familyRootNode.addChild(Either.left(level1Family1));
        familyRootNode.addChild(Either.right(level1PersonChild1));
        familyRootNode.addChild(Either.left(level1Family2));
        familyRootNode.addChild(Either.left(level1Family3));
        familyRootNode.addChild(Either.left(level1Family4));
        maternalUncle = new MaternalUncle(familyRootNode);
    }

    @Test
    public void getPersonsShouldReturnCorrectMaternalUncle() throws CustomException {
        level1Family4.addChild(Either.left(level2Family3));
        level1Family4.addChild(Either.left(level2Family4));
        ArrayList expected = new ArrayList();
        expected.add(level1Family1.bornChild);
        expected.add(level1PersonChild1);
        expected.add(level1Family2.bornChild);
        expected.add(level1Family3.bornChild);
        ArrayList result = (ArrayList<Person>) maternalUncle.getPersons(level2Family3.bornChild.getName());
        Assert.assertEquals(expected, result);
    }
}