package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Models.mainSearch;
import Services.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService service;
	@GetMapping("/")
	public String add(Model model) {
		List<mainSearch> temp = service.getAll();
		model.addAttribute("order",temp);
		return "index";
		
	}
	@PostMapping("/search")
	public String doSearch(@ModelAttribute("productSearchFormData")mainSearch formData, Model model) {
		mainSearch temp = service.findByID(formData.getUserID());
		if (temp == null) {
			String oops = "no results were found";
			model.addAttribute("order",service.getAll());
			return oops;
		}
		model.addAttribute("order",temp);
		return "index";
		
	}

}
