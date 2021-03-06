package org.motechproject.openmrs19.domain;

import org.motechproject.openmrs19.resource.model.Concept;

import java.util.Objects;

/**
 * Represents a single concept name. Also stores information about its locale and type.
 */
public class OpenMRSConceptName {

    public static final String DEFAULT_LOCALE = "en";
    public static final String DEFAULT_CONCEPT_NAME_TYPE = "FULLY_SPECIFIED";

    private String name;
    private String locale;
    private String conceptNameType;

    /**
     * Creates a concept name from the given {@code name}. Uses default locale and concept type.
     *
     * @param name  the name of the concept
     */
    public OpenMRSConceptName(String name) {
        this(name, DEFAULT_LOCALE, DEFAULT_CONCEPT_NAME_TYPE);
    }

    /**
     * Creates a concept name from the given {@code name}. Uses given {@code locale} and {@code conceptNameType}.
     *
     * @param name  the name of the concept
     * @param locale  the locale of the concept name
     * @param conceptNameType  the type of the concept name
     */
    public OpenMRSConceptName(String name, String locale, String conceptNameType) {
        this.name = name;
        this.locale = locale;
        this.conceptNameType = conceptNameType;
    }

    /**
     * Copying constructor.
     *
     * @param conceptName  the concept name to copy
     */
    public OpenMRSConceptName(Concept.ConceptName conceptName) {
        this(conceptName.getName(), conceptName.getLocale(), conceptName.getConceptNameType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getConceptNameType() {
        return conceptNameType;
    }

    public void setConceptNameType(String conceptNameType) {
        this.conceptNameType = conceptNameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpenMRSConceptName)) {
            return false;
        }

        OpenMRSConceptName conceptName = (OpenMRSConceptName) o;

        return Objects.equals(name, conceptName.name) && Objects.equals(locale, conceptName.locale) &&
                Objects.equals(conceptNameType, conceptName.conceptNameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locale, conceptNameType);
    }
}
