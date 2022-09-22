package Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import Models.mainSearch;
import SearchRepository.searchRepo;



public class SearchServiceImpl implements SearchService{
	
	
	@Autowired
	searchRepo repo;
	

	@Override
	public mainSearch findByID(int id) {
		
	
		return repo.findById(id).get();
	}


	@Override
	public List<mainSearch> getAll() {
		
		return (List<mainSearch>) repo.findAll();
	}

	
	

}
