package learning.hui.demo.springboot.es.entity.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlBlogRepository extends JpaRepository<MysqlBlog, Integer> {
}
