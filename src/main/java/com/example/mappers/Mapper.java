package com.example.mappers;

public interface Mapper<X, Y> {

	Y mapToDTO(X entity);

	Y mapFromDTO(X dto);
}