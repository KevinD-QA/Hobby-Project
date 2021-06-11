package Mappers;

public interface Mapper<X, Y> {

	X mapToDTO(X entity);

	Y mapFromDTO(Y dto);
}