package unittests;

import mastersquirrel.*;

import static org.junit.jupiter.api.Assertions.*;

import mastersquirrel.Exeptions.ElementAlreadyExistsException;
import mastersquirrel.Exeptions.ElementNotInListException;
import mastersquirrel.entities.*;

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


    //new Tests from 13.04

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

    @Test
    public void testCollision() {
        System.out.println("testCollision");
        XY posToCheck = new XY(1,3);
        BadBeast badBeastCustom = new BadBeast(posToCheck){
            @Override
            public void nextStep(EntityContext entityContext) {
                type = EntityType.BADBEAST;
                entityContext.move(this,XY.RIGHT);
            }
        };

        entitySet.put(badBeastCustom);
        Wall wall = new Wall(new XY(2,3));
        entitySet.put(wall);

        BoardConfig boardConfig = new BoardConfig(
                new XY(15, 15),
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Board board = new Board(boardConfig);
        State state = new State(board);

        entitySet.nextStep(state.flattenBoard());

        assertSame(badBeastCustom.getPosition(), posToCheck);
    }

    @Test
    public void testMovement() {
        System.out.println("testMovement");
        XY posToCheck = new XY(4,3);
        BadBeast badBeastCustom = new BadBeast(new XY(3,3)){
            @Override
            public void nextStep(EntityContext entityContext) {
                type = EntityType.BADBEAST;
                entityContext.move(this,XY.RIGHT);
            }
        };

        entitySet.put(badBeastCustom);

        BoardConfig boardConfig = new BoardConfig(
                new XY(15, 15),
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Board board = new Board(boardConfig);
        State state = new State(board);

        entitySet.nextStep(state.flattenBoard());

        //test for movement in given direction and collision
        assertEquals(badBeastCustom.getPosition().getXLen(), posToCheck.getXLen());
        assertEquals(badBeastCustom.getPosition().getYLen(), posToCheck.getYLen());
    }

    @Test
    public void testBite() {
        System.out.println("testBite");
        XY posToCheck = new XY(3,3);
        BadBeast badBeastCustom = new BadBeast(posToCheck){
            @Override
            public void nextStep(EntityContext entityContext) {
                type = EntityType.BADBEAST;
                super.nextStep(entityContext);
            }
        };

        XY[] xyArray = {
                new XY(3,4),
                new XY(3,2),
                new XY(2,3),
                new XY(4,3),
                new XY(4,4),
                new XY(2,2),
                new XY(4,2),
                new XY(2,4),
        };
        entitySet.put(badBeastCustom);
        for (XY xy : xyArray) {
            MasterSquirrel masterSquirrelTemp = new MasterSquirrel(xy) {
                @Override
                public void nextStep(EntityContext entityContext) {
                    type = EntityType.MASTERSQUIRREL;
                }
            };
            entitySet.put(masterSquirrelTemp);
        }

        BoardConfig boardConfig = new BoardConfig(
                new XY(15, 15),
                0,
                0,
                0,
                0,
                0,
                0,
                0);

        Board board = new Board(boardConfig);
        State state = new State(board);

        entitySet.nextStep(state.flattenBoard());

        assertEquals(badBeastCustom.getRemainingBites(), badBeastCustom.getMaxBites() - 1);
    }
}