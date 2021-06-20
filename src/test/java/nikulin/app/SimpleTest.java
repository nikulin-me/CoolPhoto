package nikulin.app;


import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void test(){
        int x=1;
        int y=2;
        Assert.assertEquals(4,x*2+y);
        Assert.assertEquals(2,x*y);
    }

    @Test(expected = ArithmeticException.class)
    void error(){
        int x=0;
        int y=1/x;
    }
}
