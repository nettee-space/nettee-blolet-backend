package nettee.draft.block.domain.type;

import java.util.EnumSet;
import java.util.Set;

public enum DraftBlockStatus {
    DRAFT,
    PUBLISHED,
    DELETED;

    public static final Set<DraftBlockStatus> GENERAL_QUERY_STATUS = EnumSet.of(DRAFT, PUBLISHED);

    public static java.util.Set<DraftBlockStatus> getGeneralQueryStatus() {
        return GENERAL_QUERY_STATUS;
    }
}
