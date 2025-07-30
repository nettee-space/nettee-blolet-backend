package nettee.article.driven.rdb.entity.type.builder;

import nettee.article.driven.rdb.entity.type.builder.TypeSafeMarkers.Missing;
import nettee.article.driven.rdb.entity.type.builder.TypeSafeMarkers.Present;

public sealed interface TypeSafeMarkers permits Present, Missing {
    non-sealed interface Present extends TypeSafeMarkers {}
    non-sealed interface Missing extends TypeSafeMarkers {}
}

