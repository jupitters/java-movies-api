package com.jupitters.movieApi.dto;

import java.util.List;

public record MoviePageResponse(List<MovieDto> movieDtos,
                                Integer pageNumber,
                                Integer pageSize,
                                Integer totalElements,
                                Integer totalPages,
                                boolean isLast) {

}
