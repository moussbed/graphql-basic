package com.mb.graphqlbasic.dtos;

import java.util.Objects;

public record ProductRequest(String label,
                             Double price,
                             Integer quantity,
                             Long categoryId
                             ) {
    public boolean isValid(){
        return (Objects.nonNull(this.label)
                && !this.label.isBlank()
                && Objects.nonNull(this.price)
                && Objects.nonNull(this.quantity)
                && Objects.nonNull(this.categoryId))
                ;
    }
}
