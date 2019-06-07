package vn.com.hieu.registerPage.factories;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Converter <T, C>{

	private final Function<T, C> fromDto;
	private final Function<C, T> fromEntity;
	
	public Converter(Function<T, C> fromDto, Function<C, T> fromEntity) {
		super();
		this.fromDto = fromDto;
		this.fromEntity = fromEntity;
	}
	
	public final C convertFromDto(final T dto) {
		return fromDto.apply(dto);
	}
	
	public final T convertFromEntity(final C entity) {
		return fromEntity.apply(entity);
	}
	
	public final List<C> createFromDtos(final Collection<T> dtos) {
		return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
	}
	
	public final List<T> createFromEntities(final Collection<C> entities) {
		return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
	}
	
	
}
