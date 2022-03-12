package com.gmail.aperavoznikau.demo.service.impl;

import com.gmail.aperavoznikau.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List findAll() {
        return Collections.emptyList();
    }
}
