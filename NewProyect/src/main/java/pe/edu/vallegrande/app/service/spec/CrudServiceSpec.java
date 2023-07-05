package pe.edu.vallegrande.app.service.spec;

import java.util.List;

public interface CrudServiceSpec<T> {
	List<T> getAll();

	List<T> getAllInactive();

	List<T> get(T bean);

	void insert(T bean);

	void update(T bean);

	void delete(String id);

	void reactive(String id);
}
