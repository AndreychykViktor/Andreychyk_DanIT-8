package com.exemple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Hm7Test {


    private Human human;
    private Family family;
    private String[][] schedule;

    @BeforeEach
    void setUp() {



        schedule = new String[7][2];
        int i = 0;
        for (DayOfWeek day : DayOfWeek.values()) {
            schedule[i][0] = day.name();
            schedule[i][1] = "Activity for " + day.name();
            i++;
        }
    }

    @Test
    void testHumanToString_WithCompleteData() {

        human = new Human("John", "Doe", 1990, 120, schedule);


        String result = human.toString();


        assertTrue(result.contains("name='John'"));
        assertTrue(result.contains("surname='Doe'"));
        assertTrue(result.contains("year=1990"));
        assertTrue(result.contains("iq=120"));
        assertTrue(result.contains("schedule="));
    }

    @Test
    void testHumanToString_WithNullSchedule() {
        human = new Human("Jane", "Smith", 1995, 110, null);
        String result = human.toString();

        assertTrue(result.contains("name='Jane'"));
        assertTrue(result.contains("surname='Smith'"));
        assertTrue(result.contains("year=1995"));
        assertTrue(result.contains("iq=110"));
        assertTrue(result.contains("schedule=null"));
    }

    @Test
    void testFamilyToString_WithParentsOnly() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        String result = family.toString();


        assertTrue(result.contains("Mother:"));
        assertTrue(result.contains("name='Mary'"));
        assertTrue(result.contains("Father:"));
        assertTrue(result.contains("name='John'"));
        assertTrue(result.contains("Children: No children"));
        assertTrue(result.contains("Pet: No pet"));
    }

    @Test
    void testFamilyToString_WithChildren() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        Human child1 = new Human("Alice", "Johnson", 2000, 125, null);
        Human child2 = new Human("Bob", "Johnson", 2002, 130, null);

        family.addChild(child1);
        family.addChild(child2);


        String result = family.toString();


        assertTrue(result.contains("Mother:"));
        assertTrue(result.contains("name='Mary'"));
        assertTrue(result.contains("Father:"));
        assertTrue(result.contains("name='John'"));
        assertTrue(result.contains("name='Alice'"));
        assertTrue(result.contains("name='Bob'"));
        assertTrue(result.contains("Pet: No pet"));
    }

   @Test
   void testFamilyToString_WithPet() {
       Human mother = new Human("Mary", "Johnson", 1970, 115, null);
       Human father = new Human("John", "Johnson", 1968, 120, null);
       family = new Family(mother, father);

       Pet pet = new Pet("Dog", "Rex", 3, 75, new String[]{"sit", "fetch"});
       family.setPet(pet);

       String result = family.toString();
       System.out.println(result);

       assertTrue(result.contains("Mother:"));
       assertTrue(result.contains("Father:"));
       assertTrue(result.contains("Pet:"));
       assertTrue(result.contains("species='Dog'"));
       assertTrue(result.contains("nickname='Rex'"));
   }

    @Test
    void testDeleteChildByReference_Success() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        Human child1 = new Human("Alice", "Johnson", 2000, 125, null);
        Human child2 = new Human("Bob", "Johnson", 2002, 130, null);

        family.addChild(child1);
        family.addChild(child2);

        int initialChildrenCount = family.getChildren().length;


        boolean result = family.deleteChild(child1);


        assertTrue(result, "Method should return true when child is successfully deleted");
        assertEquals(initialChildrenCount - 1, family.getChildren().length, "Children array should be reduced by 1");


        for (Human child : family.getChildren()) {
            assertNotEquals(child1, child, "Deleted child should not be present in the array");
        }


        boolean child2Found = false;
        for (Human child : family.getChildren()) {
            if (child.equals(child2)) {
                child2Found = true;
                break;
            }
        }
        assertTrue(child2Found, "Non-deleted child should still be present in the array");
    }

    @Test
    void testDeleteChildByReference_NonExistingChild() {
        // Arrange
        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        Human child1 = new Human("Alice", "Johnson", 2000, 125, null);
        Human child2 = new Human("Bob", "Johnson", 2002, 130, null);


        family.addChild(child1);

        int initialChildrenCount = family.getChildren().length;
        Human[] initialChildren = family.getChildren().clone();


        boolean result = family.deleteChild(child2);


        assertFalse(result, "Method should return false when child isn't found");
        assertEquals(initialChildrenCount, family.getChildren().length, "Children array size should remain unchanged");

        for (int i = 0; i < initialChildren.length; i++) {
            assertEquals(initialChildren[i], family.getChildren()[i],
                    "Children array elements should remain unchanged");
        }
    }

    @Test
    void testDeleteChildByIndex_Success() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        Human child1 = new Human("Alice", "Johnson", 2000, 125, null);
        Human child2 = new Human("Bob", "Johnson", 2002, 130, null);

        family.addChild(child1);
        family.addChild(child2);

        int initialChildrenCount = family.getChildren().length;


        boolean result = family.deleteChild(0);


        assertTrue(result, "Method should return true when child is successfully deleted");
        assertEquals(initialChildrenCount - 1, family.getChildren().length, "Children array should be reduced by 1");


        for (Human child : family.getChildren()) {
            assertNotEquals(child1, child, "Deleted child should not be present in the array");
        }


        boolean child2Found = false;
        for (Human child : family.getChildren()) {
            if (child.equals(child2)) {
                child2Found = true;
                break;
            }
        }
        assertTrue(child2Found, "Non-deleted child should still be present in the array");
    }

    @Test
    void testDeleteChildByIndex_OutOfRange() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        Human child = new Human("Alice", "Johnson", 2000, 125, null);
        family.addChild(child);

        int initialChildrenCount = family.getChildren().length;
        Human[] initialChildren = family.getChildren().clone();


        boolean resultNegative = family.deleteChild(-1);


        assertFalse(resultNegative, "Method should return false for negative index");
        assertEquals(initialChildrenCount, family.getChildren().length, "Children array size should remain unchanged");


        boolean resultOutOfRange = family.deleteChild(initialChildrenCount);


        assertFalse(resultOutOfRange, "Method should return false for out of range index");
        assertEquals(initialChildrenCount, family.getChildren().length, "Children array size should remain unchanged");

        for (int i = 0; i < initialChildren.length; i++) {
            assertEquals(initialChildren[i], family.getChildren()[i],
                    "Children array elements should remain unchanged");
        }
    }

    @Test
    void testAddChild() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);

        int initialChildrenCount = family.getChildren().length;

        Human child = new Human("Alice", "Johnson", 2000, 125, null);

        family.addChild(child);

        assertEquals(initialChildrenCount + 1, family.getChildren().length,
                "Children array should increase by 1");

        assertSame(child, family.getChildren()[family.getChildren().length - 1],
                "Added child should be the last element in the array");

        assertSame(family, child.getFamily(),
                "Child should have reference to the family it was added to");
    }

    @Test
    void testCountFamily() {

        Human mother = new Human("Mary", "Johnson", 1970, 115, null);
        Human father = new Human("John", "Johnson", 1968, 120, null);
        family = new Family(mother, father);


        assertEquals(2, family.countFamily(),
                "Family with just parents should have 2 members");


        Human child1 = new Human("Alice", "Johnson", 2000, 125, null);
        family.addChild(child1);


        assertEquals(3, family.countFamily(),
                "Family with parents and one child should have 3 members");


        Human child2 = new Human("Bob", "Johnson", 2002, 130, null);
        family.addChild(child2);

        assertEquals(4, family.countFamily(),
                "Family with parents and two children should have 4 members");
    }
}