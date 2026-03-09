import org.testng.annotations.*;

public class testNGTest {


    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @Test
    public void test() {
        System.out.println("test");
    }

    @Test(priority = 0)
    public void test1() {
        System.out.println("test priority");
    }

    @Test(dependsOnMethods = "test")
    public void test2() {
        System.out.println("test depends on ");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("test priority 2");
    }

    @Test(priority = -1)
    public void test4() {
        System.out.println("test priority 4");
    }


}
