package com.dmarco.luckymoney.repository;

import com.dmarco.luckymoney.domain.Luckymoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckymoneyRepository extends JpaRepository<Luckymoney,Integer> {


}
