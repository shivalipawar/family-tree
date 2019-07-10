package com.shivali.familytree;

import com.shivali.familytree.util.Either;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Family {
    List<Either<Family, Person>> children;
    public Person spouse;
    public Person bornChild;

    public Family(Person bornChild, Person spouse) {
        this.bornChild = bornChild;
        this.spouse = spouse;
        this.children = new ArrayList();
    }

    public void addChild(Either<Family, Person> child) {
        children.add(child);
    }

    boolean contains(String name) {
        return this.bornChild.getName().equals(name) || this.spouse.getName().equals(name);
    }

    Person getMother() {
        return (bornChild.getGender() == GenderType.Female) ? bornChild : spouse;
    }

    public List<Person> getBornChildren() {
        return this.children.stream().map((child) -> {
            if (child.isLeft()) {
                Family family = child.getLeft();
                return family.bornChild;
            } else return child.getRight();
        }).collect(Collectors.toList());
    }
}
