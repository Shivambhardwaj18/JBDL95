package com.example.jbdl95minor1.services;

import com.example.jbdl95minor1.dtos.CreateStudentResponse;
import com.example.jbdl95minor1.dtos.DummyResponse;
import com.example.jbdl95minor1.dtos.GetStudentResponse;
import com.example.jbdl95minor1.models.*;
import com.example.jbdl95minor1.repositories.RedisRepository;
import com.example.jbdl95minor1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookService bookService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    RedisRepository redisRepository;


    /*
    * 1. extract user
    * 2. encode password
    * 3. save user
    * 4. update student object
    * 5. save student
    * */
    public CreateStudentResponse create(Student student) {

        User user = student.getUser();
        user = this.userService.createUser(user, Authority.STUDENT);
        student.setUser(user);

        Student savedStudent = this.studentRepository.save(student);
        return CreateStudentResponse.builder()
                .id(savedStudent.getId())
                .createdOn(savedStudent.getCreatedOn())
                .build();

//        return savedStudent.getBooks();
    }

    public GetStudentResponse getStudent(Integer id) {

//        hit
        Student student = this.redisRepository.get(id);

        if(student != null) {
            return GetStudentResponse.builder()
                    .student(student)
                    .build();
        }

//         miss

        student = this.studentRepository.findById(id).orElse(null);

        if(student != null) {
            this.redisRepository.create(student);
        }

        return GetStudentResponse.builder()
                .student(student)
                .build();

    }

    public Student merge(Student incomingStudent, Student existingStudent){

        HashMap<String,Object> incoming = this.objectMapper.convertValue(incomingStudent, HashMap.class);
        HashMap<String ,Object> existing = this.objectMapper.convertValue(existingStudent,HashMap.class);

        for(String key:incoming.keySet()){
            if(incoming.get(key)!=null){
                existing.put(key,incoming.get(key));
            }
        }

        Student merged = this.objectMapper.convertValue(existing,Student.class);

        return merged;

    }


    public GetStudentResponse update(Student student, Integer id) {

        Student savedStudent = this.studentRepository.findById(id).orElse(null);

        Student merged = this.merge(student,savedStudent);

        this.studentRepository.save(merged);

        return GetStudentResponse.builder()
                .student(student)
                .build();
    }

    public void delete(Integer id) {

        this.studentRepository.deActivate(id, StudentStatus.INACTIVE);
    }

//    public DummyResponse createWithList(Student student, Boolean bookList) {
//        Student savedStudent = this.studentRepository.save(student);
//
//        List<Book> books = null;
//        if(bookList){
//            books = this.bookService.getBooksByStudentId(savedStudent.getId());
//        }
//        return DummyResponse.builder().id(savedStudent.getId())
//                .bookList(books)
//                .createdOn(savedStudent.getCreatedOn())
//                .build();
//
//    }
}


/*
*
* handle delete cases
* handle update cases
*
* */
