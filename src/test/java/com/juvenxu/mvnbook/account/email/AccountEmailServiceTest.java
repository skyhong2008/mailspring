package com.juvenxu.mvnbook.account.email;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("huwg@tiros.com.cn", "xxxxx");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx
                .getBean("accountEmailService");

        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendMail("huwg@tiros.com.cn", subject, htmlText);

        greenMail.waitForIncomingEmail(3000, 1);

        // Message[] msgs = greenMail.getReceivedMessages();
        // assertEquals(1, msgs.length);
        // assertEquals(subject, msgs[0].getSubject());
        // assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();
    }
}
