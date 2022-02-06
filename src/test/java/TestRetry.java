import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRetry {

    @Test()
    public void retryFailingTests(){
        Assert.assertEquals(12, 10);
    }

}
