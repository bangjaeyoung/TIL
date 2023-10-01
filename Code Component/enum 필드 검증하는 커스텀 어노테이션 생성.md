enum 클래스 안의 값들만 들어올 수 있도록 검증하는 커스텀 어노테이션 생성 코드

### 1. EnumValue 인터페이스
``` Java
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValue {
    String[] acceptedValues();

    String message() default "Invalid value. Accepted values are {acceptedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

<br>

### 2. ConstraintValidator 인터페이스를 구현하는 EnumValidator 클래스
``` Java
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValue, String> {
    private String[] acceptedValues;

    @Override
    public void initialize(EnumValue annotation) {
        acceptedValues = annotation.acceptedValues();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (String validValue : acceptedValues) {
            if (validValue.equals(value)) {
                return true;
            }
        }

        return false;
    }
}

```

<br>

### 3. 커스텀 어노테이션 적용
``` Java
// 해당 enum 클래스를 참조하는 필드에 커스텀 어노테이션 적용
@EnumValue(acceptedValues = {"valueOfEnumClass1", "valueOfEnumClass1"})
@Enumerated(EnumType.STRING)
private EnumClass enumClass;
```
