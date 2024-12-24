package com.itwillbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{


}
