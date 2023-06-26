package com.example.ideas.category.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.ideas.category.domain.model.Category;
import com.example.ideas.category.dto.CategoryWithStatisticsDto;

import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query(value = "select new com.example.ideas.category.dto.CategoryWithStatisticsDto(" +
            "c.id, c.name, count(distinct q), count(a)) " +
            "from Category c " +
            "left join c.questions q " +
            "left join q.answers a " +
            "group by c.id ")
    List<CategoryWithStatisticsDto> findAllWithStatistics();
}
