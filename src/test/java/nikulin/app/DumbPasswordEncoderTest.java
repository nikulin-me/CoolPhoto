package nikulin.app;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DumbPasswordEncoderTest {

    @Test
    void encode() {
        DumbPasswordEncoder dumbPasswordEncoder = new DumbPasswordEncoder();
        Assert.assertEquals("secret: 'mypwd'",dumbPasswordEncoder.encode("mypwd"));
        Assert.assertThat(dumbPasswordEncoder.encode("pass"), Matchers.containsString("pass"));
    }
}