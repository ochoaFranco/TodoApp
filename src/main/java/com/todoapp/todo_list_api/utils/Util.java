    package com.todoapp.todo_list_api.utils;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class Util {
        private final ModelMapper mapper;

        public Util(ModelMapper mapper) {
            this.mapper = mapper;
        }

        // Generic method to map from one type to another
        public <S, T>  T convert(S from, Class<T> toClass) {
            return mapper.map(from, toClass);
        }
    }
