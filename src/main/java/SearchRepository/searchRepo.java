package SearchRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Models.mainSearch;

@Repository
public interface searchRepo extends CrudRepository<mainSearch, Integer>{

}
