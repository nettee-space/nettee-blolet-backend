package nettee.draft.draftblock.domain.type;

import java.util.EnumSet;
import java.util.Set;

public enum DraftBlockStatus {
    PENDING,
    PUBLISHED,
    REMOVED;

    public static final Set<DraftBlockStatus> GENERAL_QUERY_STATUS = EnumSet.of(PENDING, PUBLISHED);

    public static java.util.Set<DraftBlockStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
