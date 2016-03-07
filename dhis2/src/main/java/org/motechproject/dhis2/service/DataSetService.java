package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.DataSet;
import org.motechproject.dhis2.rest.domain.DataSetDto;

/**
 * Manages CRUD operations for the {@link DataSet} entities.
 */
public interface DataSetService {

    /**
     * Creates an instance of the {@link DataSet} class based on the information given in the {@code dto}.
     *
     * @param dto  the information about a data set
     * @return the created instance
     */
    DataSet createFromDetails(DataSetDto dto);

    /**
     * Deletes all instances of the {@link DataSet} from the MOTECH database.
     */
    void deleteAll();
}
