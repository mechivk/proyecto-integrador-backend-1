package com.proyectointegrador.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    public T register(T t);
    public Optional<T> find(Integer id);
    public T update(T t);
    public void delete(Integer id);
    public List<T> list();
}
