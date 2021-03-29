package com.jitu.testpack;

import com.jitu.base.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * @author Jitendra
 * @since 29-03-2021
 * @project Shopping
 */
public class LogsTest extends Page {

    @Test
    public void logsCh()
    {
        logInfo("Info");
        logPass("Pass");
        initBrowser();
        Assert.assertEquals(1,2);
        logFail("Fail");
        logSkip("Skip");
        logWarning("Warning");
        tearDown();
    }
}
