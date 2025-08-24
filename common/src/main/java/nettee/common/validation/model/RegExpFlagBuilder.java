package nettee.common.validation.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class RegExpFlagBuilder {
    private final Set<RegexpFlag> flags = new HashSet<>();

    private RegExpFlagBuilder() {}

    public static RegExpFlagBuilder builder() {
        return new RegExpFlagBuilder();
    }

    public RegExpFlagBuilder add(RegexpFlag flag) {
        flags.add(flag);
        return this;
    }

    public RegExpFlagBuilder addAll(Collection<RegexpFlag> flags) {
        this.flags.addAll(flags);
        return this;
    }

    public RegExpFlagBuilder remove(RegexpFlag flag) {
        flags.remove(flag);
        return this;
    }

    public String build() {
        return String.join("", flags.stream()
                .map(RegexpFlag::value)
                .collect(Collectors.toSet())
        );
    }

    public enum RegexpFlag {
        S,
        U,
        M,
        I;

        public String value() {
            return name().toLowerCase();
        }
    }
}