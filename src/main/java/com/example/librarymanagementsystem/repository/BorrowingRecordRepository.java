package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, String> {

    Optional<BorrowingRecord> findByPatron_IdAndBook_Id(Long partonId, Long bookId);

    @Query("SELECT COALESCE(MAX(br.id),0) FROM BorrowingRecord br ")
    Long getMaxId();
}
