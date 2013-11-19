package org.motechproject.sms.http.osgi;

import org.motechproject.config.core.domain.BootstrapConfig;
import org.motechproject.config.core.domain.ConfigSource;
import org.motechproject.config.core.domain.DBConfig;
import org.motechproject.config.service.ConfigurationService;
import org.motechproject.event.listener.EventListenerRegistryService;
import org.motechproject.event.listener.EventRelay;
import org.motechproject.sms.api.service.SendSmsRequest;
import org.motechproject.sms.api.service.SmsService;
import org.motechproject.testing.osgi.BaseOsgiIT;
import org.motechproject.testing.utils.Wait;
import org.motechproject.testing.utils.WaitCondition;
import org.motechproject.testing.utils.server.RequestInfo;
import org.motechproject.testing.utils.server.StubServer;
import org.osgi.framework.ServiceReference;

import java.net.URL;

import static java.util.Arrays.asList;


public class SmsHttpServiceBundleIT extends BaseOsgiIT {

    public static final int PORT = 8282;
    public static final int MAX_WAIT_TIME = 2000;
    public static final int WAIT_DURATION_BETWEEN_CHECKS = 10;
    private StubServer stubServer;

    @Override
    protected void onSetUp() throws Exception {
        stubServer = new StubServer(PORT, "/sms").start();
    }

    public void testThatSMSShouldBeSentToSMSGateway() throws Exception {

        assertNotNull(bundleContext.getServiceReference(EventRelay.class.getName()));
        assertNotNull(bundleContext.getServiceReference(EventListenerRegistryService.class.getName()));

        ServiceReference configurationServiceRef = bundleContext.getServiceReference(ConfigurationService.class.getName());
        assertNotNull(configurationServiceRef);

        ConfigurationService configurationService = (ConfigurationService) bundleContext.getService(configurationServiceRef);

        URL url = Thread.currentThread().getContextClassLoader().getResource("sms-http-template.json");

        BootstrapConfig bootstrap = new BootstrapConfig(new DBConfig("http://localhost:5984/_utils", "" , ""), "", ConfigSource.UI);
        configurationService.save(bootstrap);
        configurationService.loadBootstrapConfig();

        configurationService.saveRawConfig("org.motechproject.motech-sms-http-bundle", "sms-http-template.json",
                url.openStream());

        ServiceReference smsServiceRef = bundleContext.getServiceReference(SmsService.class.getName());
        assertNotNull(smsServiceRef);

        SmsService smsService = (SmsService) getApplicationContext().getBean("smsServiceRef");

        smsService.sendSMS(new SendSmsRequest(asList("9999"), "Hello"));

        new Wait(stubServer, new WaitCondition() {
            @Override
            public boolean needsToWait() {
                return stubServer.waitingForRequests();
            }
        }, WAIT_DURATION_BETWEEN_CHECKS, MAX_WAIT_TIME).start();

        RequestInfo requestInfo = stubServer.detailForRequest("/sms");

        assertNotNull(requestInfo);
        assertEquals("/sms", requestInfo.getContextPath());
        assertEquals("Hello", requestInfo.getQueryParam("message"));
        assertEquals("9999", requestInfo.getQueryParam("recipients"));
    }


    @Override
    protected void onTearDown() throws Exception {
        stubServer.stop();
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"/META-INF/spring/testSmsHttpBundleContext.xml"};
    }
}
