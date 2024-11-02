package dev.jlkeesh.module9.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@ParameterObject
public class AuthRoleCriteria extends GenericCriteria {

    @Override
    public Sort defaultSort() {
        return Sort.by(Sort.Direction.ASC, "id");
    }
}
