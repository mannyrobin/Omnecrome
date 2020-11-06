package ru.armd.pbk.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class PropsLogger {

    public static final Logger LOGGER = Logger.getLogger(PropsLogger.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private PropertySourcesPlaceholderConfigurer properties;

    @PostConstruct
    public void logProperties() {
        LOGGER.info("activeProfile: " + activeProfile);

        for (EnumerablePropertySource propertySource : findPropertiesPropertySources()) {
            LOGGER.info("********** " + propertySource.getName() + " **********");
            String[] propertyNames = propertySource.getPropertyNames();
            Arrays.sort(propertyNames);
            for (String propertyName : propertyNames) {
                LOGGER.info(propertyName + "=" + propertySource.getProperty(propertyName));
            }
        }
    }

    private List<EnumerablePropertySource> findPropertiesPropertySources() {
        List<EnumerablePropertySource> propertiesPropertySources = new LinkedList<>();
        for (PropertySource<?> propertySource : properties.getAppliedPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                propertiesPropertySources.add((EnumerablePropertySource) propertySource);
            }
        }
        return propertiesPropertySources;
    }

}
