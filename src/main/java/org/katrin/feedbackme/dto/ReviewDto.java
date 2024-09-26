package org.katrin.feedbackme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private long id;
    private String comment;
    private int rating;
    private long authorId;
    private long recipientId;
}
