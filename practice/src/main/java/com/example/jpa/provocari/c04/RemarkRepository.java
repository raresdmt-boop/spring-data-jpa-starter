package com.example.jpa.provocari.c04;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemarkRepository extends JpaRepository<Remark, Long> {

    default Page<Remark> findByLikesGreaterThan(int minLikes, Pageable pageable) {
        throw new UnsupportedOperationException("TODO c04");
    }
}
