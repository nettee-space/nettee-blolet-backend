package nettee.draft.domain.type;

import java.util.EnumSet;
import java.util.Set;

public enum DraftStatus {
    REMOVED,
    PENDING,
    UPDATED,
    PUBLISHED;

    public static final Set<DraftStatus> GENERAL_QUERY_STATUS = EnumSet.of(PENDING);

    public static Set<DraftStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
