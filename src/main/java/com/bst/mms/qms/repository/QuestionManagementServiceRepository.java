package com.bst.mms.qms.repository;

import com.bst.mms.qms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionManagementServiceRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByTopicId(Integer topicId);

    Question findByTopicIdAndId(Integer topicId, Integer id);

    @Query(value = "SELECT * FROM question q WHERE q.topic_id=:topicId AND q.difficulty_level=:difficultyLevel ORDER BY RANDOM() LIMIT :questionCount",
            nativeQuery = true)
    List<Question> findRandomQuestionsByTopicIdAndDifficultyLevel(Integer topicId, Integer difficultyLevel,
                                                                  int questionCount);
}
