package sen3004.app7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sen3004.app7.model.Author;
import sen3004.app7.model.Book;
import sen3004.app7.service.App7Service;

@Controller
public class App7Controller {

	@Autowired
	App7Service service;

	@GetMapping("/book/{bid}")
	public ModelAndView viewBook(@PathVariable long bid) {
		ModelAndView mv = new ModelAndView("view-book");
		mv.addObject("book", service.findBookById(bid));
		
		return mv;
	}
	
	@GetMapping("/author/{aid}")
	public ModelAndView viewAuthor(@PathVariable long aid) {
		ModelAndView mv = new ModelAndView("view-author");
		mv.addObject("author", service.findAuthorById(aid));
		
		return mv;
	}
	
	@GetMapping("/author/{aid}/add-book/{bid}")
	public ModelAndView addBookToAuthor(@PathVariable long aid, @PathVariable long bid) {
		ModelAndView mv = new ModelAndView("view-author");
		Author author = service.findAuthorById(aid);
		Book book = service.findBookById(bid);
		author.getBooks().add(book);
		service.saveAuthor(author);
		mv.addObject("author", author);
		
		return mv;
	}
	
	@GetMapping("/author/{aid}/del-book/{bid}")
	public ModelAndView deleteBookFromAuthor(@PathVariable long aid, @PathVariable long bid) {
		ModelAndView mv = new ModelAndView("view-author");
		Author author = service.findAuthorById(aid);
		Book book = service.findBookById(bid);
		author.getBooks().remove(book);
		service.saveAuthor(author);
		mv.addObject("author", author);
		
		return mv;
	}
	
	@GetMapping("/get-author/{name}")
	public ModelAndView getAuthor(@PathVariable String name) {
		ModelAndView mv = new ModelAndView("get-author");
		mv.addObject("authors", service.findAuthorByName(name));
		
		return mv;
	}
	
	@GetMapping("/show-book-list")
	public ModelAndView getBooks(@RequestParam(required = false) Integer page) {
		ModelAndView mv = new ModelAndView("book-list");
		
		if(page == null || page < 1)
			page = 1; // The default page index is zero-based. We are using one as the first page index instead of zero.
		
		Page<Book> result = service.findBooks(PageRequest.of(page-1, 5)); // Since the default page index is zero-based, it is necessary to decrease the page number by one.
		
		mv.addObject("books", result.toList());
		mv.addObject("page_count", result.getTotalPages());
		mv.addObject("current_page", page);
		
		return mv;
	}
	
}
