package unittests;

import mastersquirrel.*;

import static org.junit.jupiter.api.Assertions.*;

import mastersquirrel.entities.AEntity;
import mastersquirrel.entities.BadBeast;
import mastersquirrel.entities.GoodBeast;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;

public class EntitySetTest {

    private final EntitySet entitySet = EntitySet.getInstance();
    private final GoodBeast goodBeast1 = new GoodBeast(new XY(1,1));
    private final BadBeast badBeast1 = new BadBeast(new XY(1,1));
    private final TestEntity testEntity = new TestEntity(new XY(1,1));

    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("-------START TEST-------");
        entitySet.clear();
    }

    @AfterEach
    public void tearDown() throws Exception{
        System.out.println();
    }

    @Test
    public void testPutAndCheckIfInList(){
        System.out.println("testPutAndCheckIfInList");
        entitySet.put(goodBeast1);

        assertNotNull(entitySet.get(goodBeast1.getID()));
    }

    @Test
    public void testPullAndCheckIfNotInList(){
        System.out.println("testPullAndCheckIfNotInList");
        entitySet.put(goodBeast1);
        entitySet.pull(goodBeast1.getID());

        assertNull(entitySet.get(goodBeast1.getID()));
    }

    @Test
    public void testIfOnlyOneIsPulled(){
        System.out.println("testIfOnlyOneIsPulled");
        entitySet.put(goodBeast1);
        entitySet.put(badBeast1);

        entitySet.pull(goodBeast1.getID());
        assertNotNull(entitySet.get(badBeast1.getID()));
    }

    @Test
    public void testDoublePut(){
        System.out.println("testDoublePut");
        entitySet.put(goodBeast1);
        try{
            entitySet.put(goodBeast1);
            fail("testDoublePut");
        }
        catch (ElementAlreadyExistsException e){
            //good
        }
    }

    @Test
    public void testPullNotInList(){
        System.out.println("testPullNotInList");
        try{
            entitySet.pull(goodBeast1.getID());
            fail("testPullNotInList");
        }
        catch (ElementNotInListException e){
            //good
        }
    }

    @Test
    public void testNextStep(){
        System.out.println("testNextStep");
        testEntity.nextStep(null);

        assertTrue(testEntity.hasStepped);
    }


    //new Tests

    @Test
    public void testEnumerateForward(){
        System.out.println("testEnumerateForward");
        AEntity[] aEntities = {badBeast1,goodBeast1,testEntity};
        entitySet.put(badBeast1);
        entitySet.put(goodBeast1);
        entitySet.put(testEntity);

        boolean condition = false;

        Enumeration e = entitySet.enumerateForward();
        for (int i = 0; e.hasMoreElements(); i++){
            AEntity aEntity = (AEntity)e.nextElement();
            condition = (aEntity.getID() == aEntities[i].getID());
        }
        assertTrue(condition);
    }

    @Test
    public void testEnumerateBackwards(){
        System.out.println("testEnumerateBackwards");
        AEntity[] aEntities = {badBeast1,goodBeast1,testEntity};
        entitySet.put(badBeast1);
        entitySet.put(goodBeast1);
        entitySet.put(testEntity);

        boolean condition = false;

        Enumeration e = entitySet.enumerateBackward();
        for (int i = 1; e.hasMoreElements(); i++){
            AEntity aEntity = (AEntity)e.nextElement();
            condition = (aEntity.getID() == aEntities[aEntities.length-i].getID());
        }
        assertTrue(condition);
    }

    @Test
    public void testEnumerateRandom(){
        System.out.println("testEnumerateRandom");
        AEntity[] aEntities = {goodBeast1,badBeast1,testEntity};
        entitySet.put(badBeast1);
        entitySet.put(goodBeast1);
        entitySet.put(testEntity);

        boolean condition = false;

        Enumeration e = entitySet.enumerateRandom(42069);

        for (int i = 0; e.hasMoreElements(); i++){
            AEntity aEntity = (AEntity)e.nextElement();
            condition = (aEntity.getID() == aEntities[i].getID());
        }
        assertTrue(condition);
    }
}