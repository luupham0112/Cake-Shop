package com.phanmem.cakeshop.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data

public class CategoryDto {

    private Long categoryId;
    private String name;
}
