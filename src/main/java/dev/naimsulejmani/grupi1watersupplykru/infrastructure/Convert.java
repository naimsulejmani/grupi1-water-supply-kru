package dev.naimsulejmani.grupi1watersupplykru.infrastructure;

public interface Convert<TDto, TEntity> {
    TDto toDto(TEntity entity);
    TEntity toEntity(TDto dto);
}
