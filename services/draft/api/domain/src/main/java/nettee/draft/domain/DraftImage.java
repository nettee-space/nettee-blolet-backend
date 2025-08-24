package nettee.draft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DraftImage {

    private Long id;

    private String imageUrl;

    private LocalDateTime createdAt;
}