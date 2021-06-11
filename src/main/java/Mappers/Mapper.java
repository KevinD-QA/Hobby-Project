package Mappers;

public interface Mapper<X, Y> {

	Y mapToDTO(X entity);

	Y mapFromDTO(X dto);
}