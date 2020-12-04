package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;


@Configuration
@EnableWebFlux
public class ApplicationConfiguration implements WebFluxConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GenericConverter() {
            @Override
            public Set<ConvertiblePair> getConvertibleTypes() {
                return Set.of(new ConvertiblePair(String.class, SafeEnum.class));
            }

            @Override
            public Object convert(final Object source, final TypeDescriptor sourceType, final TypeDescriptor targetType) {
                if (SafeEnum.class.equals(sourceType.getType())) {
                    return source;
                }

                if (String.class.equals(sourceType.getType())) {
                    try {
                        Method forValue = targetType.getType().getDeclaredMethod("forValue", String.class);
                        return forValue.invoke(targetType.getType(), source);
                    } catch (NoSuchMethodException e) {
                        throw new IllegalStateException(
                                "Missing function to deserialize " + targetType.getName() + " type");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException("Cannot deserialize " + targetType.getName() + " type", e);
                    }
                }

                return source;
            }

        });
    }


}