package org.motechproject.alerts.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.alerts.contract.AlertCriteria;
import org.motechproject.alerts.contract.AlertService;
import org.motechproject.alerts.contract.AlertsDataService;
import org.motechproject.alerts.domain.Alert;
import org.motechproject.alerts.domain.AlertStatus;
import org.motechproject.alerts.domain.AlertType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlertServiceImplTest {

    @Mock
    private AlertsDataService alertsDataService;

    @Mock
    private AlertFilter alertFilter;

    private AlertService alertService;

    @Before
    public void setup() {
        initMocks(this);

        alertService = new AlertServiceImpl();
        ((AlertServiceImpl) alertService).setAlertsDataService(alertsDataService);
        ((AlertServiceImpl) alertService).setAlertFilter(alertFilter);
    }

    @Test
    public void shouldSearchForAlerts() {
        AlertCriteria criteria = new AlertCriteria().byStatus(AlertStatus.NEW).byType(AlertType.LOW).byExternalId("entity_id").byPriority(2);
        List<Alert> alerts = mock(List.class);

        when(alertFilter.search(criteria)).thenReturn(alerts);

        assertEquals(alerts, alertService.search(criteria));
    }

    @Test
    public void shouldCreateANewAlert() {
        Map<String, String> data = new HashMap<String, String>();
        alertService.create("entity_id", "name", "description", AlertType.HIGH, AlertStatus.CLOSED, 0, data);
        ArgumentCaptor<Alert> alertCaptor = ArgumentCaptor.forClass(Alert.class);
        verify(alertsDataService).create(alertCaptor.capture());
        Alert alert = alertCaptor.getValue();
        assertEquals("entity_id", alert.getExternalId());
        assertEquals("name", alert.getName());
        assertEquals("description", alert.getDescription());
        assertEquals(AlertType.HIGH, alert.getAlertType());
        assertEquals(AlertStatus.CLOSED, alert.getStatus());
        assertEquals(0, alert.getPriority().intValue());
        assertEquals(data, alert.getData());
    }
}
