package com.ownproject.ServiceOrganizer.Services;


import com.ownproject.ServiceOrganizer.Model.Role;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SetConverter implements Converter<String, Set<Role>> {


@Override
    public Set<Role> convert(String source) {
        return Arrays.stream(StringUtils.trimAllWhitespace("[]").split(","))
                .map(StringUtils::trimAllWhitespace)
                .map(Role::new)
                .collect(Collectors.toSet());

    }
}
