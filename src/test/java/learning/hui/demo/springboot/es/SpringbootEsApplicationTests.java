package learning.hui.demo.springboot.es;

import learning.hui.demo.springboot.es.entity.es.EsBlog;
import learning.hui.demo.springboot.es.repository.es.EsBlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest
class SpringbootEsApplicationTests {

	@Autowired
	EsBlogRepository blogRepository;
	@Test
	void contextLoads() {

	}
	@Test
	public void testEs(){
		long count = blogRepository.count();
		System.out.println("........."+ count);
//		Iterable<EsBlog> all = blogRepository.findAll();
//		Iterator<EsBlog> iterator = all.iterator();
//		EsBlog next = iterator.next();
//		System.out.println("-------" + next.getTitle());
	}

}
