package ch.unil.doplab.grocerystorewebsite.v1.tests;

import ch.unil.doplab.grocerystorewebsite.v1.controllers.FoodController;
import ch.unil.doplab.grocerystorewebsite.v1.controllers.LoginController;
import ch.unil.doplab.grocerystorewebsite.v1.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v1.models.Food;
import ch.unil.doplab.grocerystorewebsite.v1.models.User;
import ch.unil.doplab.grocerystorewebsite.v1.database.MockDatabase;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class FoodControllerTest {

    public FoodControllerTest() {
        System.out.println("FoodControllerTest constructor");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("@BeforeClass setUpClass");
        // we initialize our UserList (database for users) and add users to it
        MockDatabase.addAUser(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
        MockDatabase.addAUser(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
        MockDatabase.addAUser(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
        MockDatabase.addAUser(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("@AfterClass tearDownClass");
        MockDatabase.getUsers().clear();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("@BeforeMethod setUp");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("@AfterMethod tearDown");
    }

    // test IndexOutOfBoundsException
    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        System.out.println("@Test testIndexOutOfBoundsException");
        MockDatabase.getUsers().get(100);
    }

    // test timeout
    @Test(timeOut = 1000)
    public void testTimeOut() throws InterruptedException {
        System.out.println("@Test testTimeOut");
        Thread.sleep(500);
    }

    @Test
    public void testAddFoodToShoppingCart() throws DoesNotExistException {
        System.out.println("@Test testAddFoodToShoppingCart");
        // login as a user
        LoginController.setUsername("marge");
        LoginController.setPassword("1234");
        LoginController.userLogsIn();
        // we need some mock data
        MockDatabase.addFood(new Food("Pizza", 12.0, new ArrayList<String>() {
            {
                add("dough");
                add("tomato sauce");
                add("mozarella");
            }
        }));
        String foodName = "Pizza";
        // add the food to the shopping cart
        FoodController.setFoodName(foodName);
        FoodController.addFoodToShoppingCart();
        // find the food in the shopping cart
        Food expectedFood = null;
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                expectedFood = f;
            }
        }
        // if the food exists, expectedFood must have a value
        Assert.assertNotNull(expectedFood);
    }

    @Test
    public void testRemoveFoodFromShoppingCart() throws DoesNotExistException {
        System.out.println("@Test testRemoveFoodFromShoppingCart");
        // login as a user
        LoginController.setUsername("marge");
        LoginController.setPassword("1234");
        LoginController.userLogsIn();
        // we need some mock data
        MockDatabase.addFood(new Food("Pizza", 12.0, new ArrayList<String>() {
            {
                add("dough");
                add("tomato sauce");
                add("mozarella");
            }
        }));
        String foodName = "Pizza";
        // remove the food
        FoodController.setFoodName(foodName);
        FoodController.removeFoodFromShoppingCart();
        // it should not exist in the shopping cart
        Food expectedFood = null;
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                expectedFood = f;
            }
        }
        // if the food does not exist, expectedFood must be null
        Assert.assertNull(expectedFood);
    }

    // this test fails
//    @Test(timeOut = 10)
//    public void testFailingTimeOut() throws InterruptedException {
//        System.out.println("@Test failTimeOut");
//        Thread.sleep(500);
//    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void testNumberFormatException() {
        System.out.println("@Test testNumberFormatException");
        int n = Integer.parseInt(null);
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testClassCastException() {
        System.out.println("@Test testClassCastException");
        Object o = Integer.valueOf(42);
        String s = (String) o;

    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testArithmeticException() {
        System.out.println("@Test testArithmeticException");
        int result = 7 / 0;
    }
}
