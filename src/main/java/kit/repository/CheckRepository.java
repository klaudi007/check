package kit.repository;

import kit.model.Check;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check,Long>{
	public List<Check> findAll();
	public Check findOne(Long id);
}
