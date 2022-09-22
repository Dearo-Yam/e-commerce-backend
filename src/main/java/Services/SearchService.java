package Services;

import java.util.List;


import Models.mainSearch;

public interface SearchService {
	public mainSearch findByID(int id);
	public List<mainSearch> getAll();
	

}
