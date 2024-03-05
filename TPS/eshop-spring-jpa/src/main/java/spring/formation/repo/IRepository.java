package spring.formation.repo;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, PK> {
	public List<T> findAll();
	public Optional<T> findById(PK id);
	public T save(T entity);
	public void deleteById(PK id);
}
