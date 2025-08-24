package nettee.common.validation.model.interfaces;

import lombok.Builder;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;
import static nettee.common.validation.Preconditions.validateRegex;
import static nettee.common.validation.model.interfaces.ValidationPreparationErrorCode.INVALID_REGEXP_PATTERN;
import static nettee.common.validation.model.interfaces.ValidationPreparationErrorCode.UNSUPPORTED_REGEXP_FLAG;

@Builder
public record RegexpSpec(
        String pattern,
        String flags
) {
    public RegexpSpec {
        assert pattern != null : "The 'pattern' must not be null or blank.";

        if (flags == null) {
            flags = "u";
        }
        validateRegex(flags, "^[sumi]*$", UNSUPPORTED_REGEXP_FLAG);

        try {
            // Bits:
            //  s 0010 0000 (32)
            //  u 0100 0000 (64)
            //  m 0000 1000 ( 8)
            //  i 0000 0010 ( 2)
            int flagInt = flags.chars()
                    .map((ch) -> switch (ch) {
                        case 's' -> DOTALL;
                        case 'u' -> UNICODE_CHARACTER_CLASS; // NOTE UNICODE_CLASS 대신 UNICODE_CHARACTER_CLASS 사용할 것.
                        case 'm' -> MULTILINE;
                        case 'i' -> CASE_INSENSITIVE;
                        default -> throw UNSUPPORTED_REGEXP_FLAG.exception();
                    })
                    .reduce(0, (a,b) -> a | b);

            //noinspection MagicConstant
            Pattern.compile(pattern, flagInt);
        } catch (PatternSyntaxException e) {
            throw INVALID_REGEXP_PATTERN.exception(e);
        }
    }
}
