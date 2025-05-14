package com.exemple;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    import static org.junit.jupiter.api.Assertions.*;

    public class Hm7Test {
        private Human human;
        private Family family;
        private Map<DayOfWeek, String> schedule;

        @BeforeEach
        void setUp() {
            schedule = new HashMap<>();
            for (DayOfWeek day : DayOfWeek.values()) {
                schedule.put(day, "Activity for " + day.name());
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
            human = new Human("Jane", "Smith", 1995, 110, (Map<DayOfWeek, String>) null);
            String result = human.toString();

            assertTrue(result.contains("name='Jane'"));
            assertTrue(result.contains("surname='Smith'"));
            assertTrue(result.contains("year=1995"));
            assertTrue(result.contains("iq=110"));
            assertTrue(result.contains("schedule={}"));
        }

        @Test
        void testFamilyToString_WithParentsOnly() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
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
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            Human child1 = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            Human child2 = new Human("Bob", "Johnson", 2002, 130, (Map<DayOfWeek, String>) null);

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
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
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
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            Human child1 = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            Human child2 = new Human("Bob", "Johnson", 2002, 130, (Map<DayOfWeek, String>) null);

            family.addChild(child1);
            family.addChild(child2);

            int initialChildrenCount = family.getChildren().size();

            boolean result = family.deleteChild(child1);

            assertTrue(result, "Method should return true when child is successfully deleted");
            assertEquals(initialChildrenCount - 1, family.getChildren().size(), "Children list should be reduced by 1");

            assertFalse(family.getChildren().contains(child1), "Deleted child should not be present in the list");
            assertTrue(family.getChildren().contains(child2), "Non-deleted child should still be present in the list");
        }

        @Test
        void testDeleteChildByReference_NonExistingChild() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            Human child1 = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            Human child2 = new Human("Bob", "Johnson", 2002, 130, (Map<DayOfWeek, String>) null);

            family.addChild(child1);

            int initialChildrenCount = family.getChildren().size();
            List<Human> initialChildren = new ArrayList<>(family.getChildren());

            boolean result = family.deleteChild(child2);

            assertFalse(result, "Method should return false when child isn't found");
            assertEquals(initialChildrenCount, family.getChildren().size(), "Children list size should remain unchanged");
            assertEquals(initialChildren, family.getChildren(), "Children list elements should remain unchanged");
        }

        @Test
        void testDeleteChildByIndex_Success() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            Human child1 = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            Human child2 = new Human("Bob", "Johnson", 2002, 130, (Map<DayOfWeek, String>) null);

            family.addChild(child1);
            family.addChild(child2);

            int initialChildrenCount = family.getChildren().size();

            boolean result = family.deleteChild(0);

            assertTrue(result, "Method should return true when child is successfully deleted");
            assertEquals(initialChildrenCount - 1, family.getChildren().size(), "Children list should be reduced by 1");
            assertFalse(family.getChildren().contains(child1), "Deleted child should not be present in the list");
            assertTrue(family.getChildren().contains(child2), "Non-deleted child should still be present in the list");
        }

        @Test
        void testDeleteChildByIndex_OutOfRange() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            Human child = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            family.addChild(child);

            int initialChildrenCount = family.getChildren().size();
            List<Human> initialChildren = new ArrayList<>(family.getChildren());

            boolean resultNegative = family.deleteChild(-1);
            assertFalse(resultNegative, "Method should return false for negative index");
            assertEquals(initialChildrenCount, family.getChildren().size(), "Children list size should remain unchanged");

            boolean resultOutOfRange = family.deleteChild(initialChildrenCount);
            assertFalse(resultOutOfRange, "Method should return false for out of range index");
            assertEquals(initialChildrenCount, family.getChildren().size(), "Children list size should remain unchanged");
            assertEquals(initialChildren, family.getChildren(), "Children list elements should remain unchanged");
        }

        @Test
        void testAddChild() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            int initialChildrenCount = family.getChildren().size();

            Human child = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);

            family.addChild(child);

            assertEquals(initialChildrenCount + 1, family.getChildren().size(), "Children list should increase by 1");
            assertSame(child, family.getChildren().get(family.getChildren().size() - 1),
                    "Added child should be the last element in the list");
            assertSame(family, child.getFamily(), "Child should have reference to the family it was added to");
        }

        @Test
        void testCountFamily() {
            Human mother = new Human("Mary", "Johnson", 1970, 115, (Map<DayOfWeek, String>) null);
            Human father = new Human("John", "Johnson", 1968, 120, (Map<DayOfWeek, String>) null);
            family = new Family(mother, father);

            assertEquals(2, family.countFamily(), "Family with just parents should have 2 members");

            Human child1 = new Human("Alice", "Johnson", 2000, 125, (Map<DayOfWeek, String>) null);
            family.addChild(child1);

            assertEquals(3, family.countFamily(), "Family with parents and one child should have 3 members");

            Human child2 = new Human("Bob", "Johnson", 2002, 130, (Map<DayOfWeek, String>) null);
            family.addChild(child2);

            assertEquals(4, family.countFamily(), "Family with parents and two children should have 4 members");
        }
    }