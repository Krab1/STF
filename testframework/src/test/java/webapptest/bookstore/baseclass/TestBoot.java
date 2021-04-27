package webapptest.bookstore.baseclass;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = { com.krab.stfbase.boot.StfBootstrapper.class })
public abstract class TestBoot extends AbstractTestNGSpringContextTests {
}
